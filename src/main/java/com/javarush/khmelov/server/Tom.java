package com.javarush.khmelov.server;

import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.Servlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import org.apache.catalina.Context;
import org.apache.catalina.filters.SetCharacterEncodingFilter;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tom extends Tomcat {

    /*
        move all from webapp to resources
        and add to pom.xml

        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-el</artifactId>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jul-to-slf4j</artifactId>
        </dependency>
     */

    private final Context context;

    public static void run() {
        run(8080);
    }

    public static void run(int port) {
        Tom tom = new Tom(port);
        tom.start();
        System.out.println("Tom started on http://localhost:" + port + "/");
    }

    public Tom(int port) {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        this.setPort(port);
        URL rootURL = Objects.requireNonNull(this.getClass().getResource("/"));
        String path = rootURL.getPath();
        String root = new File(path).getAbsolutePath();
        this.getHost().setAppBase(root);
        context = this.addWebapp("", root);
        scanComponents(rootURL);
    }

    @Override
    public void start() {
        try {
            super.start();
            this.getConnector().start();
        } catch (org.apache.catalina.LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    private void scanComponents(URL rootURL) {
        try {
            URI uri = Objects.requireNonNull(rootURL).toURI();
            Path appPackage = Path.of(uri);
            try (Stream<Path> walk = Files.walk(appPackage)) {
                String suffix = ".class";
                Set<String> names = walk.map(Path::toString)
                        .filter(o -> o.endsWith(suffix))
                        .map(s -> s.substring(s.indexOf("classes") + 8))
                        .map(s -> s.replace(suffix, ""))
                        .map(s -> s.replace(File.separator, "."))
                        .collect(Collectors.toSet());
                List<Servlet> servlets = new ArrayList<>();
                List<Filter> filters = new ArrayList<>();
                for (String name : names) {
                    Class<?> clazz = Class.forName(name);
                    if (clazz.isAnnotationPresent(WebServlet.class)) {
                        servlets.add((Servlet) clazz.getDeclaredConstructor().newInstance());
                    }
                    if (clazz.isAnnotationPresent(WebFilter.class)) {
                        filters.add((Filter) clazz.getDeclaredConstructor().newInstance());
                    }
                }
                addAnnotatedServlets(servlets.toArray(Servlet[]::new));
                addAnnotatedFilters(filters.toArray(Filter[]::new));
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAnnotatedServlets(Servlet... servlets) {
        for (Servlet servlet : servlets) {
            Class<? extends Servlet> servletClass = servlet.getClass();
            org.apache.catalina.Wrapper wrapper = this.addServlet("", servletClass.getSimpleName(), servlet);
            if (servletClass.isAnnotationPresent(MultipartConfig.class)) {
                MultipartConfig multipartConfig = servletClass.getAnnotation(MultipartConfig.class);
                wrapper.setMultipartConfigElement(new MultipartConfigElement(multipartConfig));
            }
            WebServlet annotationParam = servletClass.getAnnotation(WebServlet.class);
            wrapper.setLoadOnStartup(annotationParam.loadOnStartup());
            Stream.of(annotationParam.value(), annotationParam.urlPatterns())
                    .flatMap(Arrays::stream)
                    .forEach(wrapper::addMapping);
        }
    }

    private void addAnnotatedFilters(Filter... filters) {
        SetCharacterEncodingFilter utfSupport = new SetCharacterEncodingFilter();
        utfSupport.setEncoding(StandardCharsets.UTF_8.name());
        add(utfSupport);
        for (Filter filter : filters) {
            add(filter);
        }
    }

    private void add(Filter filter) {
        Class<? extends Filter> filterClass = filter.getClass();
        String name = filterClass.getName();
        FilterDef filterDef = new FilterDef();
        filterDef.setFilter(filter);
        filterDef.setFilterName(name);
        context.addFilterDef(filterDef);

        FilterMap filterMap = new FilterMap();
        filterMap.setFilterName(name);
        if (filterClass.isAnnotationPresent(WebFilter.class)) {
            WebFilter annotationParam = filterClass.getAnnotation(WebFilter.class);
            Stream.of(annotationParam.value(), annotationParam.urlPatterns())
                    .flatMap(Arrays::stream)
                    .forEach(filterMap::addURLPattern);
        } else {
            filterMap.addURLPattern("/*");
        }
        context.addFilterMap(filterMap);
    }
}
