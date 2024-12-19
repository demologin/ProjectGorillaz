package com.javarush.borisov.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.db.DbConfig.MultiKey;
import com.javarush.borisov.entity.Contragent;
import com.javarush.borisov.entity.Equipment;
import com.javarush.borisov.entity.Request;
import com.javarush.borisov.util.EntityCreator;
import com.javarush.borisov.util.FolderScanner;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Db {
    public List<Contragent> contragents;
    public Map<MultiKey, HashSet<String>> allRequests = new HashMap<>();
    public Map<Integer,List<Integer>> allDatesWeGot;
    public List<Request> requestsToShow = new ArrayList<>();


    public Db() {
        EntityCreator entityCreator = ClassCreator.get(EntityCreator.class);
        FolderScanner folderScanner = ClassCreator.get(FolderScanner.class);
        contragents = (entityCreator.create("contragents.json", Contragent.class));
        List<String> folders = folderScanner.scan("data/req", true);
        calculateAllRequests(folders);
        allDatesWeGot = allDatesWeGot();


        for(int i=0;i<10;i++){
            Request request = new Request(contragents.getFirst(), "APOS-123" + "" + i,
                    "ООО Рога и Копыта","+7-903-258-33-33",List.of(new Equipment("1111","2")),
                    List.of(new Equipment(null,null)),LocalDateTime.now(),null,
                    "Г Новокузнецк ул Ленина 21");
            requestsToShow.add(request);
        }
    }

    private void calculateAllRequests(List<String> folders) {
        for (String folder : folders) {
            MultiKey multiKey;
            String[] tokens = folder.split("/");
            try {
                multiKey = new MultiKey(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            }catch (NumberFormatException e) {
                throw new RuntimeException("Invalid folder path: " + folder);
            }
            Map<MultiKey, HashSet<String>> result = new TreeMap<>();
            URL pathToReqFileList = Db.class.getClassLoader().getResource("data/req" + "/" + folder + "/reqinfolder.db");
            try {
                HashSet<String> requestInFolder = new ObjectMapper().readValue(pathToReqFileList, HashSet.class);
                allRequests.put(multiKey, requestInFolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Map<Integer,List<Integer>> allDatesWeGot(){
        Map<Integer,List<Integer>> result = new TreeMap<>();
        for (MultiKey multiKey : allRequests.keySet()) {
            result.computeIfAbsent(multiKey.getYear(), k -> new ArrayList<>()).add(multiKey.getMonth());
        }
        return result;
    }
}
