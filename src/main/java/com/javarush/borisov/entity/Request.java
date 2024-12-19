package com.javarush.borisov.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javarush.borisov.constants.RequestStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Getter@Setter
public class Request {

    private final Contragent contragent;
    private final String requestNumber;
    private final String customer;
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime closeDate;
    private String address;
    private Path linkToAktFile;
    private RequestStatus status;
    @NonNull
    private List<Equipment> equipmentsTransferred;
    @NonNull
    private List<Equipment> equipmentsReceived;
    private Map<String,String> parameters;

    public Request(Contragent contragent, String requestNumber, String customer, String phoneNumber,
                   List<Equipment> equipmentsTransferred, List<Equipment> equipmentsReceived, LocalDateTime createDate, LocalDateTime closeDate,
                   String address) {
        this.contragent = contragent;
        this.requestNumber = requestNumber;
        this.customer = customer;
        this.phoneNumber = phoneNumber;
        this.equipmentsTransferred = equipmentsTransferred;
        this.equipmentsReceived = equipmentsReceived;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.address = address;
        this.status = RequestStatus.ASSIGNED;
    }

}
