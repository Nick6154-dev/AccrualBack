package uce.edu.ec.accrual.models.object;

import uce.edu.ec.accrual.models.entity.AccrualData;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Network;
import uce.edu.ec.accrual.models.entity.Person;

public class RegisterObject {

    private Person person;

    private Docent docent;

    private AccrualData accrualData;

    private Network network;

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
