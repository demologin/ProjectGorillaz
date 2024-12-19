package com.javarush.borisov.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.db.DbConfig.MultiKey;
import com.javarush.borisov.entity.Contragent;
import com.javarush.borisov.entity.Request;
import com.javarush.borisov.util.EntityCreator;
import com.javarush.borisov.util.FolderScanner;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Getter
public class Db {
    public List<Contragent> contragents;
    public Map<MultiKey, HashSet<String>> allRequests = new HashMap<>();
    public Map<String,List<String>> allDatesWeGot;
    public List<Request> requestsToShow = new ArrayList<>();


    public Db() {
        EntityCreator entityCreator = ClassCreator.get(EntityCreator.class);
        FolderScanner folderScanner = ClassCreator.get(FolderScanner.class);
        contragents = (entityCreator.create("contragents.json", Contragent.class));
        List<String> folders = folderScanner.scan("data/req", true);
        calculateAllRequests(folders);
        allDatesWeGot = allDatesWeGot();
    }

    private void calculateAllRequests(List<String> folders) {
        for (String folder : folders) {
            String[] tokens = folder.split("/");
            MultiKey multiKey = new MultiKey(tokens[0], tokens[1]);
            URL pathToReqFileList = Db.class.getClassLoader().getResource("data/req" + "/" + folder + "/reqinfolder.db");
            try {
                HashSet<String> requestInFolder = new ObjectMapper().readValue(pathToReqFileList, HashSet.class);
                allRequests.put(multiKey, requestInFolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Map<String,List<String>> allDatesWeGot(){
        Map<String,List<String>> result = new TreeMap<>();
        for (MultiKey multiKey : allRequests.keySet()) {
            result.computeIfAbsent(multiKey.getYear(), k -> new ArrayList<>()).add(multiKey.getMonth());
        }
        return result;
    }
}
