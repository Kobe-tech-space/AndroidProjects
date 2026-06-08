package com.example.course7_client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.net.Uri;
import static android.provider.ContactsContract.*;

import com.example.course7_client.entity.Contact;

import java.util.ArrayList;

public class ContactAddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_contact_name;
    private EditText et_contact_phone;
    private EditText et_contact_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        et_contact_name = findViewById(R.id.et_contact_name);
        et_contact_phone = findViewById(R.id.et_contact_phone);
        et_contact_email = findViewById(R .id.et_contact_email);
        findViewById(R.id.btn_add_contact).setOnClickListener(this);
        findViewById(R.id.btn_read_contact).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_contact:
                Contact contact = new Contact();
                contact.name = et_contact_name.getText().toString().trim();
                contact.phone = et_contact_phone.getText().toString().trim();
                contact.email = et_contact_email.getText().toString().trim();
                addFullContacts(getContentResolver(),contact);
                break;
            case R.id.btn_read_contact:
                readPhoneContacts(getContentResolver());
                break;
        }
    }
    private void addContacts(ContentResolver resolver,Contact contact) {
        ContentValues values = new ContentValues();
        Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI,values);
        long rawContactId = ContentUris.parseId(uri);
        ContentValues name = new ContentValues();
        name.put(Contacts.Data.RAW_CONTACT_ID,rawContactId);
        name.put(Contacts.Data.MIMETYPE,CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        name.put(Contacts.Data.DATA2,contact.name);
        resolver.insert(ContactsContract.Data.CONTENT_URI,name);

        ContentValues phone = new ContentValues();
        phone.put(Contacts.Data.RAW_CONTACT_ID,rawContactId);
        phone.put(Contacts.Data.MIMETYPE,CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        phone.put(Contacts.Data.DATA1,contact.phone);
        phone.put(Contacts.Data.DATA2,CommonDataKinds.Phone.TYPE_MOBILE);
        resolver.insert(ContactsContract.Data.CONTENT_URI,phone);

        ContentValues email = new ContentValues();
        email.put(Contacts.Data.RAW_CONTACT_ID,rawContactId);
        email.put(Contacts.Data.MIMETYPE,CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        email.put(Contacts.Data.DATA1,contact.email);
        email.put(Contacts.Data.DATA2,CommonDataKinds.Email.TYPE_WORK);
        resolver.insert(ContactsContract.Data.CONTENT_URI,email);
    }
    private void addFullContacts(ContentResolver resolver,Contact contact) {
        ContentProviderOperation op_main = ContentProviderOperation
                .newInsert(RawContacts.CONTENT_URI)
                .withValue(RawContacts.ACCOUNT_NAME,null)
                .build();
        ContentProviderOperation op_name = ContentProviderOperation
                .newInsert(Data.CONTENT_URI)
                .withValueBackReference(Contacts.Data.RAW_CONTACT_ID,0)
                .withValue(Contacts.Data.MIMETYPE,CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(Contacts.Data.DATA2,contact.name)
                .build();
        ContentProviderOperation op_phone = ContentProviderOperation
                .newInsert(Data.CONTENT_URI)
                .withValueBackReference(Data.RAW_CONTACT_ID, 0)
                .withValue(Data.MIMETYPE, CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(Data.DATA1, contact.phone)
                .withValue(Data.DATA2, CommonDataKinds.Phone.TYPE_MOBILE)
                .build();
        ContentProviderOperation op_email = ContentProviderOperation
                .newInsert(Data.CONTENT_URI)
                .withValueBackReference(Data.RAW_CONTACT_ID, 0)
                .withValue(Data.MIMETYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(Data.DATA1, contact.email)
                .withValue(Data.DATA2, CommonDataKinds.Email.TYPE_WORK)
                .build();
        ArrayList<ContentProviderOperation> operations = new ArrayList<>();
        operations.add(op_main);
        operations.add(op_name);
        operations.add(op_phone);
        operations.add(op_email);
        try {
            resolver.applyBatch(AUTHORITY,operations);
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @SuppressLint("Range")
    private void readPhoneContacts(ContentResolver resolver) {
        Cursor cursor = resolver.query(RawContacts.CONTENT_URI,
                new String[]{RawContacts._ID},
                null,null,null,null);
        while (cursor.moveToNext()) {
            int rawContactID = cursor.getInt(0);
            Uri uri = Uri.parse("content://com.android.contacts/contacts/" + rawContactID + "/data");
            Cursor dataCursor = resolver.query(uri,
                    new String[]{Contacts.Data.MIMETYPE,Contacts.Data.DATA1,Contacts.Data.DATA2},
                    null,null,null,null);
            Contact contact = new Contact();
            while (dataCursor.moveToNext()) {
                String data1 = dataCursor.getString(dataCursor.getColumnIndex(Contacts.Data.DATA1));
                String mimeType = dataCursor.getString(dataCursor.getColumnIndex(Contacts.Data.MIMETYPE));
                switch (mimeType)  {
                    case CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE:
                        contact.name = data1;
                        break;
                    case CommonDataKinds.Email.CONTENT_ITEM_TYPE:
                        contact.email = data1;
                        break;
                    case CommonDataKinds.Phone.CONTENT_ITEM_TYPE:
                        contact.phone = data1;
                        break;
                }
            }
            dataCursor.close();
            if (contact.name != null) {
                Log.d("legion",contact.toString());
            }
        }
        cursor.close();
    }
}