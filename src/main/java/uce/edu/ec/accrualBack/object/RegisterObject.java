package uce.edu.ec.accrualBack.object;

import uce.edu.ec.accrualBack.entity.AccrualData;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Network;
import uce.edu.ec.accrualBack.entity.Person;

public class RegisterObject {

    private Person person;

    private Docent docent;

    private AccrualData accrualData;

    private Network network;

    public RegisterObject() {
    }

    public RegisterObject(Person person, Docent docent) {
        this.person = person;
        this.docent = docent;
    }

    public RegisterObject(Person person, Docent docent, AccrualData accrualData, Network network) {
        this.person = person;
        this.docent = docent;
        this.accrualData = accrualData;
        this.network = network;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Docent getDocent() {
        return docent;
    }

    public void setDocent(Docent docent) {
        this.docent = docent;
    }

    public AccrualData getAccrualData() {
        return accrualData;
    }

    public void setAccrualData(AccrualData accrualData) {
        this.accrualData = accrualData;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
