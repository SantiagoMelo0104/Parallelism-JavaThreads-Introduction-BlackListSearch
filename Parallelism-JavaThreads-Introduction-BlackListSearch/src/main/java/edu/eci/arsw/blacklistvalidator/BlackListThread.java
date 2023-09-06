package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class BlackListThread extends Thread{

    int start;
    int end;
    HostBlacklistsDataSourceFacade skds;
    String ipaddress;
    LinkedList<Integer> blackListOcurrences;
    int checkedListsCount = 0;
    AtomicInteger ocurrencesCount = new AtomicInteger(0);
    int BLACK_LIST_ALARM_COUNT=5;

    /**
     * @param start
     * @param end
     * @param skds
     * @param ipaddress
     * @param blackListOcurrences
     */
    public BlackListThread (int start, int end, HostBlacklistsDataSourceFacade skds, String ipaddress,LinkedList<Integer> blackListOcurrences) {
        this.start = start;
        this.end = end;
        this.skds = skds;
        this.ipaddress = ipaddress;
        this.blackListOcurrences = blackListOcurrences;
    }

    @Override
    public void run() {

        for (int i=start;i<end;i++){
            if (blackListOcurrences.size()==BLACK_LIST_ALARM_COUNT){
                stop();
                break;
            }
            if (skds.isInBlackListServer(i, ipaddress)){
                blackListOcurrences.add(i);
                ocurrencesCount.addAndGet(1);
            }
            checkedListsCount++;
        }
    }

    public int getOcurrencesCount () {
        return ocurrencesCount.get();
    }

    public int getCheckedListsCount(){
        return checkedListsCount;
    }
    public LinkedList<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }

}