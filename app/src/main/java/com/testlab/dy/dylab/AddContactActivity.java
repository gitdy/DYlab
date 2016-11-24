package com.testlab.dy.dylab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContent();
            }
        });

    }

    private void addContent() {
        boolean result = insert("kaka", "110", "111@qq.com",
                "111");
        System.out.println("insert result " + result);
        Toast.makeText(this, "insert result " + result, Toast.LENGTH_SHORT).show();
    }

    public boolean insert(String given_name, String mobile_number,
                          String work_email, String im_qq) {
        try {
            ContentValues values = new ContentValues();

            // 下面的操作会根据RawContacts表中已有的rawContactId使用情况自动生成新联系人的rawContactId
            Uri rawContactUri = getContentResolver().insert(
                    RawContacts.CONTENT_URI, values);
            long rawContactId = ContentUris.parseId(rawContactUri);

            // 向data表插入姓名数据
            if (given_name != "") {
                values.clear();
                values.put(Data.RAW_CONTACT_ID, rawContactId);
                values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
                values.put(StructuredName.GIVEN_NAME, given_name);
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                        values);
            }

            // 向data表插入电话数据
            if (mobile_number != "") {
                values.clear();
                values.put(Data.RAW_CONTACT_ID, rawContactId);
                values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
                values.put(Phone.NUMBER, mobile_number);
                values.put(Phone.TYPE, Phone.TYPE_MOBILE);
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                        values);
            }

            // 向data表插入Email数据
            if (work_email != "") {
                values.clear();
                values.put(Data.RAW_CONTACT_ID, rawContactId);
                values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
                values.put(Email.DATA, work_email);
                values.put(Email.TYPE, Email.TYPE_WORK);
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                        values);
            }

            // 向data表插入QQ数据
            if (im_qq != "") {
                values.clear();
                values.put(Data.RAW_CONTACT_ID, rawContactId);
                values.put(Data.MIMETYPE, Im.CONTENT_ITEM_TYPE);
                values.put(Im.DATA, im_qq);
                values.put(Im.PROTOCOL, Im.PROTOCOL_QQ);
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                        values);
            }
            // 向data表插入头像数据
            Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ic_launcher);
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            // 将Bitmap压缩成PNG编码，质量为100%存储
            sourceBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            byte[] avatar = os.toByteArray();
            values.put(Data.RAW_CONTACT_ID, rawContactId);
            values.put(Data.MIMETYPE, Photo.CONTENT_ITEM_TYPE);
            values.put(Photo.PHOTO, avatar);
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI,
                    values);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
