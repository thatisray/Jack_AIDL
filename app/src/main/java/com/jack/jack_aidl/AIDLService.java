package com.jack.jack_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {

    private final String TAG = "Server";

    private List<Book> mBookList;

    public AIDLService( ) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList = new ArrayList<>();
        initData();
        Log.e(TAG,"数据初始化完成");
    }

    private void initData() {
        Book book1 = new Book("活着1");
        Book book2 = new Book("活着2");
        Book book3 = new Book("活着3");
        Book book4 = new Book("活着4");
        Book book5 = new Book("活着5");
        Book book6 = new Book("活着6");
        mBookList.add(book1);
        mBookList.add(book2);
        mBookList.add(book3);
        mBookList.add(book4);
        mBookList.add(book5);
        mBookList.add(book6);
        Log.e(TAG,"数据初始化完成");
    }


    private final BookController.Stub mStub = new BookController.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBookInOut( Book book ) throws RemoteException {
            if (book != null){
                book.setName("服务器改了新的书的名字 InOout");
                mBookList.add(book);
            }{
                Log.e(TAG,"接受到了一个新对象 InOut");
            }

        }
    };

    @Nullable
    @Override
    public IBinder onBind( Intent intent ) {
        return mStub;
    }
}
