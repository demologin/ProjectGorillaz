package com.javarush.borisov.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.borisov.config.ClassCreator;
import com.javarush.borisov.constants.RequestStatus;
import com.javarush.borisov.constants.UserRules;
import com.javarush.borisov.db.DbConfig.MultiKey;
import com.javarush.borisov.entity.Contragent;
import com.javarush.borisov.entity.Equipment;
import com.javarush.borisov.entity.Request;
import com.javarush.borisov.entity.User;
import com.javarush.borisov.util.EntityCreator;
import com.javarush.borisov.util.FolderScanner;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Db {
    public List<Contragent> contragents;
    public Map<MultiKey, HashSet<String>> allRequests ;
    public Map<Integer,List<Integer>> allDatesWeGot;
    public List<Request> requestsToShow = new ArrayList<>();
    public List<User> users = new ArrayList<>();


    public Db() {
        EntityCreator entityCreator = ClassCreator.get(EntityCreator.class);
        FolderScanner folderScanner = ClassCreator.get(FolderScanner.class);
        contragents = (entityCreator.create("contragents.json", Contragent.class));
        List<String> folders = folderScanner.scan("data/req", true);
        allRequests =  calculateAllRequests(folders);
        allDatesWeGot = allDatesWeGot();

//начало инициализации данных для примера
        for(int i=0;i<100;i++){
            Request request = new Request(contragents.getFirst(), "APOS-123" + "" + i,
                    "ООО Рога и Копыта","+7-903-258-33-33",List.of(new Equipment("PAX S300","123456")),
                    List.of(new Equipment(null,null)),LocalDateTime.now(), LocalDateTime.now().plusHours(24),null,
                    "654044, Кемеровская область, Новокузнецк, р-н Новоильинский, ул Новоселов, 21");
            request.setStatus(RequestStatus.ASSIGNED);
            requestsToShow.add(request);

        }
        User user1 = new User("Сергей","serg@mail.ru","1234", UserRules.ADMIN);
        User user2 = new User("Миша","miha@mail.ru","1234", UserRules.ENGINEER);
        User user3 = new User("Наташа","nata@mail.ru","1234", UserRules.COORDINATOR);
        users = List.of(user1,user2,user3);

//конец инициализации заявок для примера

    }

    private Map<MultiKey, HashSet<String>> calculateAllRequests(List<String> folders) {
        Map<MultiKey, HashSet<String>> result = new TreeMap<>();
        for (String folder : folders) {
            MultiKey multiKey;
            String[] tokens = folder.split("/");
            try {
                multiKey = new MultiKey(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            }catch (NumberFormatException e) {
                throw new RuntimeException("Invalid folder path: " + folder);
            }

            URL pathToReqFileList = Db.class.getClassLoader().getResource("data/req" + "/" + folder + "/reqinfolder.db");
            try {
                HashSet<String> requestInFolder = new ObjectMapper().readValue(pathToReqFileList, HashSet.class);
                result.put(multiKey, requestInFolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return result;
    }


    public Map<Integer,List<Integer>> allDatesWeGot(){
        Map<Integer,List<Integer>> result = new TreeMap<>();
        for (MultiKey multiKey : allRequests.keySet()) {
            result.computeIfAbsent(multiKey.getYear(), k -> new ArrayList<>()).add(multiKey.getMonth());
        }
        return result;
    }
}
