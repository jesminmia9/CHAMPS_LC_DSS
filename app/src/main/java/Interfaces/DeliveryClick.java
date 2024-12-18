package Interfaces;

import android.app.Dialog;

import forms_datamodel.tmpDelivery_DataModel;

public interface DeliveryClick {
    void onClickDelivery(Dialog dialog, String tmpDeliveryDataModel, String NBID, String PGN, String HHID, String MemID, String Title);
}
