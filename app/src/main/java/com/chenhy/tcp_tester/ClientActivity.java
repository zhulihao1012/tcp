package com.chenhy.tcp_tester;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Set;

/**
 * Create By HauyuChen
 * Email：Hauyu.Chen@gmail.com
 */

/* 客户端界面代码 */
public class ClientActivity extends Activity {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
private String a="0x";
private  String tp;

    private   String  hexString="A0030172EA";
    private String[] partarr = new String[]{"101000000000000000000027",
                                              "343138313230303130313031","343138313230303130313032","343138313230303130313033"
    ,"343138313230303130313034","343138313230303130313035","343138313230303130313036","343138313230303130313037","343138313230303130313038","343138313230303130313039"

    };

    private String[] ceshiarr = new String[]{"343138313230303130313030", "343138313230303130313032","343138313230303130313033","343138313230303130313034"
            ,"343138313230303130313035","343138313230303130313035","343138313230303130313039","343138313230303130313037","343138313230303130313038","343138313230303130313139"

    };


    byte[]sb=hexString.getBytes();

private String bS;
    private byte[]msg=new byte[5];
    private  byte[]msg1=new byte[5];
    private  byte[]msg2=new byte[6];
    private  byte[]msg3=new byte[5];
    private final String TAG="ClientActivity";
    private EditText edit_ip;
    private EditText edit_port;
    private EditText edit_send;
    private EditText edit_recv;
    private Button btn_connect;
    private Button btn_send,btn_send1,btn_send2,btn_send3;
    private boolean isConnected = false;
    Socket socket = null;
 private    InputStream mBufferedReaderClient=null;
 private  String   subStr;

    BufferedWriter writer = null;
    BufferedReader reader = null;
    private byte line;
    private int len;
  //  private byte[]buf;
    private String strOutput;
//private String[] nu=null;
    List strList = new ArrayList();
    List  relist=new ArrayList();
    List firstList=new ArrayList();
    private String[]arr1=new String[]{"1585193","180151","1304182","1585193","907277","106439"};
    private String[]arr2=new String[]{"1585193","180151","1304182","1585193","907277","106439"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        /* 初始化 */
        edit_ip = (EditText) findViewById(R.id.edit_ip);
        edit_port = (EditText) findViewById(R.id.edit_port);
        edit_send = (EditText) findViewById((R.id.edit_msgsend));
        edit_recv = (EditText) findViewById(R.id.edit_recv);
        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_send = (Button) findViewById(R.id.btn_send);
//        btn_send1 = (Button)findViewById(R.id.btn_send1);
//        btn_send2 = (Button)findViewById(R.id.btn_send2);
//        btn_send3 = (Button)findViewById(R.id.btn_send3);
        /* 连接按钮 */
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 连接按钮处理函数 */
                connect();
            }
        });
        /* 发送按钮 */
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 发送按钮处理函数 */
                send();
            }
        });
//        btn_send1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /* 发送按钮处理函数 */
//                send1();
//            }
//        });
//        btn_send2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /* 发送按钮处理函数 */
//                send2();
//            }
//        });
//        btn_send3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                /* 发送按钮处理函数 */
//                send3();
//            }
//        });

//        for (String o : arr1) {
//            /*
//             * 筛选出两个数组中相同的值，>= 0 表示相同，< 0 表示不同 。
//             * 有相同值是返回元素的下标值。
//             * 此处采用的是 "二分搜索法来搜索指定数组"。
//             * */
//            if(Arrays.binarySearch(arr2, o) >= 0){
//
//                System.out.println(o);
//            }
//        }


        //    Log.e("CCC", "cc"+Arrays.binarySearch(partarr,strList.toArray())>=0);


//         for (int i=0;i<arr1.length;i++){
//             for (int j=0;j<arr2.length;j++){
//                 if (arr1[i]==arr2[j]){
//
//                 Log.e("lll","ll");
//
//                 }
//
//             }
//         }

//        Set<Integer> sameElementSet = getIds(arr1,arr2);
//
//        for (Integer i : sameElementSet) {
//
//            System.out.println(i);
//
//        }
//        for (Integer i:sameElementSet){
//
//
//        }


        }


    public static Set<Integer> getIds(Integer[] a, Integer[] b){

        Set<Integer> same = new HashSet<Integer>();  //用来存放两个数组中相同的元素
        Set<Integer> temp = new HashSet<Integer>();  //用来存放数组a中的元素

        for (int i = 0; i < a.length; i++) {
               same.add(a[i]);   //把数组a中的元素放到Set中，可以去除重复的元素


     //   for (int i = 0; i< b.length; i++) {
            //把数组b中的元素添加到temp中
            //如果temp中已存在相同的元素，则temp.add（b[j]）返回false
            if (!temp.add(b[i])) {
                same.add(b[i]);
                same.add(0);
            } else {

                same.add(1);
            }
        }
     //   }

        return temp ;
    }


    /* 定义Handler对象 */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        /* 当有消息发送出来的时候就执行Handler的这个方法 */
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /* 更新UI */
          //  edit_recv.append(strOutput);
            //      Editable text = edit_recv.getText(line);
//            Log.e("IIII", (String) strList.get(0));

  //          Log.e("uuuuu",Arrays.toString(strList.toArray()));
//            Log.e("eeee", (String) strList.toArray()[0]);
         //   Log.e("ee",(String)strList.toArray()[i])
         //   Log.e("mmm",Arrays.toString(partarr));

            Log.e("pppp",tp);


//            int len=tp.length();
//            int sub=24;
//            if (len%sub!=0){
//                for (int t=18;t<tp.length()-(len%sub) ;t+=24 ) {

                    //    while (true) {
                    //    if (start >= strOutput.length())


                    //      for (int l=0;l<strOutput.length();l++) {


                    String tr = tp.substring(18, 42);
                    Log.e("poiuyt", tr);



            for(int i=0; i<partarr.length;i++){

//<0 错误 ;>=0 正确.
            //     if ( Arrays.binarySearch(new Object[]{strList.toArray()[i]}, partarr[i])<0) {
             //   if ( Arrays.binarySearch(new String[]{(String) strList.toArray()[i]}, partarr[i])<0) {
             //       relist.add(0);
                    //    Log.e("XXX", "xx" + );
              //  } else {
              //      relist.add(1);
                }
            }

         //   Log.e("JJJJ","re"+strList.toArray().toString());

           // Toast.makeText(ClientActivity.this,  relist.toString(),Toast.LENGTH_SHORT).show();

            /* 调试输出 */
//            new Thread()
//            try {
//                Log.i("PDA", "----->" + mBufferedReaderClient.read(buf));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
     //   }
    };

    /* 连接按钮处理函数：建立Socket连接 */
    @SuppressLint("HandlerLeak")
    public void connect() {
        if(false == isConnected){
            final String[] str = {null};
            new Thread() {
                public void   run(){
                    String IPAdr = edit_ip.getText().toString();
                    int PORT = Integer.parseInt(edit_port.getText().toString());
                    try {
                        /* 建立socket */
                        socket = new Socket(IPAdr, PORT);
                        /* 输出流 */
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        /* 输入流 */
                 //       reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        mBufferedReaderClient=socket.getInputStream();
//                        int line=0;
//                        byte[]bu=new byte[mBufferedReaderClient.available()];
//                        while ((line=mBufferedReaderClient.read(bu))!=-1){
////
//                            strOutput=BinaryToHexString(bu);
//                                 }


                        /* 调试输出 */
                        Log.i(TAG, "输入输出流获取成功");
                        Log.i(TAG, "检测数据");
       //                 Log.i("PDA", "----->" + strOutput);

                        /* 读数据并更新UI */




      /*
      * 1
      * */

//int line=0;
//
//      byte[]buf=new byte[mBufferedReaderClient.available()];
//
//   while ((line=mBufferedReaderClient.read(buf))!=-1){
//
//       strOutput=BinaryToHexString(buf);
//   }

/*
* 2
* */

                        byte[] temp = new byte[1024];
                       int ie=0;
                       while ((ie=mBufferedReaderClient.read(temp))!=-1) {

//                        int bytes = 0;
//                        /* read从输入流socketReader中读取temp（37）数量的字节数，并将它们存储到缓冲区数组temp。实际读取的字节数作为一个整数37返回。
//                         * 此方法块，直到输入数据可用，检测到文件结束，或抛出异常。如果B的长度为零，则没有读取字节数和返回0；
//                         * 否则，将有一个至少一个字节的尝试。如果没有可用的字节，因为流是在文件的结尾，值- 1返回；否则，至少一个字节被读取和存储到temp。
//                         */
//                        bytes = mBufferedReaderClient.read(temp);
////                        if (bytes != 37) {
////                            return null;
////                        }
//                        strOutput += ByteUtil.BinaryToHexString(temp);
//
//                     //   读取报文体的内容
//                        byte[] array = new byte[2];
//                        for (int i = 1; i < 3; i++) {
//                            array[i - 1] = temp[i];
//                        }
//                        int let = ByteUtil.bytes2Short2(array);
//                        array = new byte[let];
//                        bytes = mBufferedReaderClient.read(array);
//                        if (bytes != let) {
//                            return null;
//                        }
                           strOutput = BinaryToHexString(temp);


                           Log.e("JJLLHH", "jjllhh" + strOutput);
                           //       strOutput=new String(temp);
                           //把字符串中“ ”去掉


                           //                Log.i("PDA", "----->" + strOutput);

                           String a = "1585193663213041820837";

//                        byte[]buf=new byte[mBufferedReaderClient.available()];
//
//                          mBufferedReaderClient.read(buf);
//                            strOutput=    bytes2String(buf);


//
//                        byte[]temp=new byte[2048];
//                        int LE;
//                        while ((LE=mBufferedReaderClient.read(temp,0,100))!=-1) {
//                            int bytes = 0;
//
//
//                            bytes = mBufferedReaderClient.read(temp);

                           //      strOutput = ByteUtil.BinaryToHexString(temp);

//                            byte[] buf = new byte[2];
//                            for (int i = 1; i < 3; i++) {
//                                buf[i - 1] = temp[i];
//                            }
//
//                           Message msg=handler.obtainMessage();
//                           msg.obj=strOutput;
//                           handler.sendMessage(msg);


                           Log.i("PDA", "----->" + strOutput);
                           //   String[]split=strOutput.split("\\A");
//                            for (String s:split){
//                                String sub=s.substring(0,s.indexOf("A0"));
//                                Log.e("VVVVV","vvv"+sub);
//                            }

                           //           String a="123456789321654987";
                           //    cutString(strOutput,54);
//
//                           List strList = new ArrayList();
//                           int start = 0;
//                           int end = start+27;
//
////                           while(true){
////                               if(start>=strOutput.length())
////                                   return;
//                               String t = cutString(strOutput,54);
//                               strList.add(t);
//                               System.out.println(t);
//                               Log.e("FFF", (String) strList.get(0));
//                               start = end;
//                               end = end+6;
//                               if(end>=strOutput.length()){
//                                   end = strOutput.length();
//                               }
                           //   }

                           //  StringBuffer sr = new StringBuffer("abcdefghijk123456789");
                           //   String sr="abcdefghijk123456789";
                           //   List strList = new ArrayList();

////
//                           int start = 0;
//                           int end = start + 54;
                           int len=strOutput.length();
                             int sub=54;
                             if (len%sub!=0){
                           for (int i=0;i<strOutput.length()-(len%sub) ;i+=54 ) {

                               //    while (true) {
                               //    if (start >= strOutput.length())


                               //      for (int l=0;l<strOutput.length();l++) {


                                tp = strOutput.substring(i, i + 54);

                               Log.e("kkjkj", "111" + tp + "111");

                               Message msg=handler.obtainMessage();
                               msg.obj=tp;
                               handler.sendMessage(msg);


//                               firstList.add(tp);
//                              String s="";
//
//                               for (int t = 0; t< firstList.size(); t++) {
//                                                    if (s=="") {
//                                             s= (String) firstList.get(t);}else {
//                                                        s=s+""+firstList.get(t);
//
//
//                                    Log.e("110", s);


    //   Log.e("yui",firstList.toString());

                           }






//
//                                 int len1=firstList.toString().length();
//                                 int sub1= 24;
//                                 if (len1%sub1!=0) {
//
//                                     for (int o = 18; o < firstList.toString().length() - (len1 % sub1); o += 24) {
//                                         String tr = firstList.toString().substring(o, o + 24);
//                                         Log.e("chenggonga", tr);
//
//                                     }
//                                 }


//
                               //                                   String tr = tp.substring(18, 42);

                               //                  strList.add(tr);


//
//                            char[]arr=new char[100];
//                            arr=tr.toCharArray();
//                         //   Log.e("GG", String.valueOf(arr[0]));
//
//

//                               System.out.println(tp);
//                               start = end;
//                               end = end + 54;
//                               if (end >= strOutput.length()) {
//                                   end = strOutput.length();
//                               }

                               //   Log.e("","cvb"+firstList.size());
//                                 for (int o=0;o<tp.length();o++) {
//                                     int start1 = 18;
//                                     int end1 = start1 + 24;
//                                     String tr = tp.substring(start1, end1);
//                                     strList.add(tr);
//
//
//                                     start1 = end1;
//                                     end1 = end1 + 24;
//                                     if (end1 >= tp.length()) {
//                                         end1 = tp.length();
//                                     }
//
//                                     Log.e("", "11" + strList);
////                                 }
//
//                               int start1 = 0;
//                               int end1 = start1 + 24;
//
//                               for (int i = 0; i < tp.length(); i++) {
//
//                                   String tr = tp.substring(start1, end1);
//                                   strList.add(tr);
//                                   Log.e("", "zxc" + strList);
//
//                               }
//


//                               start1 = end1;
//                               end1 = end1 + 54;
//                               if (end1 >= tp.length()) {
//                                   end1 = tp.length();
//                               }

                   //            return ;
                           }
                       }




          //    Log.e("aaaaaallllll","111"+tp);










//                           StringBuffer sr = new StringBuffer("343138313230303130313030");
//                           strList = new ArrayList();
//                           int start = 0;
//                           int end = start+26;
//
//                           while(true){
//                               if(start>=strOutput.length())
//                                   return;
//                               String te = strOutput.substring(start, end);
//                               strList.add(te);
//                               System.out.println(te);
//                               start = end;
//                               end = end+26;
//                               if(end>=strOutput.length()){
//                                   end = strOutput.length();
//                               }
//                           }








//
//                        while((i= mBufferedReaderClient.read(buf,0,100))!=-1)
//                        {
//                            line = new String(buf,0,i);
//                            Message msg = handler.obtainMessage();
//                            msg.obj = line;
//                            handler.sendMessage(msg);
//                            Log.i(TAG, "send to handler");
                            //          }
                            //    Log.i("VVVVV", "----->" + strOutput);




                    } catch (UnknownHostException e){
                        Toast.makeText(ClientActivity.this,"无法建立连接：）",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        isConnected = false;


                    }
                    catch (IOException e) {
                        Toast.makeText(ClientActivity.this,"无法建立连接：）",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        isConnected = false;

                    }
                }

            }.start();
            isConnected = true;
            /* 更新UI */
            btn_connect.setText("断开");
            Toast.makeText(ClientActivity.this,"连接成功：）",Toast.LENGTH_SHORT).show();



        }else{
            isConnected = false;
            /* 更新UI */
            btn_connect.setText("连接");
            edit_send.setText("");
            edit_recv.setText("");
            /* 关闭socket */
            onDestroy();
            Toast.makeText(ClientActivity.this,"连接已断开：）",Toast.LENGTH_SHORT).show();
        }
    }






    public static void shuju (String[] args) {

        StringBuffer str = new StringBuffer("abcdefghijk123456789");
        List strList = new ArrayList();
        int start = 0;
        int end = start+6;

        while(true){
            if(start>=str.length())
                return;
            String temp = str.substring(start, end);
            strList.add(temp);
            System.out.println(temp);
            start = end;
            end = end+6;
            if(end>=str.length()){
                end = str.length();
            }
        }
    }


    public String bytes2String(byte[] data){
        String getString = "";

        for(int i = 0; i < data.length; i++){

            getString += String.format("%02X", data[i]);

        }

        return getString;



    }

    public static String cutString(String str, int len){
        int time = str.length() / len + 1;
        for (int i = 0; i < time; i++) {
         //  String [] subStr=new String[str.length()];
String  subStr;
            if (len * (i + 1) > str.length()) {
               subStr = str.substring(len * i);
             //  subStr[i]=temp;
            } else {
             subStr = str.substring(i * len, len * (i + 1));
           //   subStr[i]=temp;
            }
            Log.e("VVV", String.valueOf(subStr));

List list=new ArrayList();

list.add(subStr);




//           Log.e("AAAAA", (String) list.get(1));

        // String [] sj=new String[subStr.length()];



//         for (int i1=0;i1<subStr.length();i1++){
//             String st =subStr.substring(18,42);
//             sj[i]=st;
//             Log.e("WW",sj[0]);
//
//         }



String []a={"老师","学生","工人","科学家"};

         Log.e("QQQQ", String.valueOf(a));



          //  Log.e("DDDD", String.valueOf(sj));
        //   Log.e("BBB", sj[1]);


        //    StringBuffer s = new StringBuffer("abcdefghijk123456789");



        }
        return str;
    }

    public static String bytesToHexFun1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }


    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }


    // 字节的转换

        //将字节数组转换为short类型，即统计字符串长度
        public static short bytes2Short2(byte[] b) {
            short i = (short) (((b[1] & 0xff) << 8) | b[0] & 0xff);
            return i;
        }

        //将字节数组转换为16进制字符串
        public static String BinaryToHexString(byte[] bytes) {
            String hexStr = "0123456789ABCDEF";
            String  result = "";
            String hex = "";
            for (byte b : bytes) {
                hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
                hex += String.valueOf(hexStr.charAt(b & 0x0F));
              //  result += hex + " ";
                result+=hex;
            }
            return result;

        }



    public static byte[] hexStrToBinaryStr(String hexString) {


        hexString = hexString.replaceAll(" ", "");

        int len = hexString.length();
        int index = 0;

        byte[] bytes = new byte[len / 2];

        while (index < len) {

            String sub = hexString.substring(index, index + 2);

            bytes[index/2] = (byte)Integer.parseInt(sub,16);

            index += 2;
        }


        return bytes;
    }

    /**
     * 将字节数组转换成十六进制的字符串
     *
     * @return
     */











    /* 发送按钮处理函数：向输出流写数据 */
    public void send() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    /* 向输出流写数据 */


                    //    byte[] test=new byte[]{(byte)0xA0,(byte)0x03,(byte)0x01,(byte)0x72,(byte)0xEA};
                    //    byte[] test=hexStringToBytes
                    msg[0]=(byte)0xA0;
                    msg[1]=(byte)0x03;
                    msg[2]=(byte)0x01;
                    msg[3]=(byte)0x72;
                    msg[4]=(byte)0xEA;
                    msg1[0]=(byte)0xA0;
                    msg1[1]=(byte)0x03;
                    msg1[2]=(byte)0x01;
                    msg1[3]=(byte)0x92;
                    msg1[4]=(byte)0xCA;
                    msg2[0]=(byte)0xA0;
                    msg2[1]=(byte)0x04;
                    msg2[2]=(byte)0x01;
                    msg2[3]=(byte)0x80;
                    msg2[4]=(byte)0xFF;
                    msg2[5]=(byte)0xDC;
                    msg3[0]=(byte)0xA0;
                    msg3[1]=(byte)0x03;
                    msg3[2]=(byte)0x01;
                    msg3[3]=(byte)0x90;
                    msg3[4]=(byte)0xCC;



                  //  byte[] bS=edit_send.getText().toString().getBytes();
              //     bS=edit_send.getText().toString();

                    //    bytesToHexFun1(bytes);

                  ///  System.out.println("11111"+sb);






                    //  writer.write(String.valueOf(bytes));

                    toHexString(sb);
//String cm="A0 03 01 72 EA";
          String cm=edit_send.getText().toString();

                 byte[]cm2=hexStrToBinaryStr(cm);

                    OutputStream socketWriter = socket.getOutputStream();
                    socketWriter.write(cm2);
                   socketWriter.flush();

       //     writer.write(ByteUtil.BinaryToHexString(bS));
//        //    writer.write(String.valueOf(msg));
//           // writer.write(String.valueOf(sb));



         //  writer.flush();





                    /* 更新UI */
            //        edit_send.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }





//    public void send1() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    /* 向输出流写数据 */
//
//
//                    //    byte[] test=new byte[]{(byte)0xA0,(byte)0x03,(byte)0x01,(byte)0x72,(byte)0xEA};
//                    //    byte[] test=hexStringToBytes
//                    msg[0]=(byte)0xA0;
//                    msg[1]=(byte)0x03;
//                    msg[2]=(byte)0x01;
//                    msg[3]=(byte)0x72;
//                    msg[4]=(byte)0xEA;
//                    msg1[0]=(byte)0xA0;
//                    msg1[1]=(byte)0x03;
//                    msg1[2]=(byte)0x01;
//                    msg1[3]=(byte)0x92;
//                    msg1[4]=(byte)0xCA;
//                    msg2[0]=(byte)0xA0;
//                    msg2[1]=(byte)0x04;
//                    msg2[2]=(byte)0x01;
//                    msg2[3]=(byte)0x80;
//                    msg2[4]=(byte)0xFF;
//                    msg2[5]=(byte)0xDC;
//                    msg3[0]=(byte)0xA0;
//                    msg3[1]=(byte)0x03;
//                    msg3[2]=(byte)0x01;
//                    msg3[3]=(byte)0x90;
//                    msg3[4]=(byte)0xCC;
//
//
//
//                    //  byte[] bS=edit_send.getText().toString().getBytes();
//                    bS=edit_send.getText().toString();
//
//                    //    bytesToHexFun1(bytes);
//
//                    System.out.println("11111"+sb);
//
//                    //  writer.write(String.valueOf(bytes));
//
//                    toHexString(sb);
//
//
//                        OutputStream socketWriter = socket.getOutputStream();
//                          socketWriter.write(msg1);
//                      socketWriter.flush();
//
//                    //     writer.write(ByteUtil.BinaryToHexString(bS));
////        //    writer.write(String.valueOf(msg));
////           // writer.write(String.valueOf(sb));
//
//
//
//                    //  writer.flush();
//
//
//
//
//
//                    /* 更新UI */
//                    //        edit_send.setText("");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//    public void send2() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    /* 向输出流写数据 */
//
//
//                    //    byte[] test=new byte[]{(byte)0xA0,(byte)0x03,(byte)0x01,(byte)0x72,(byte)0xEA};
//                    //    byte[] test=hexStringToBytes
//                    msg[0]=(byte)0xA0;
//                    msg[1]=(byte)0x03;
//                    msg[2]=(byte)0x01;
//                    msg[3]=(byte)0x72;
//                    msg[4]=(byte)0xEA;
//                    msg1[0]=(byte)0xA0;
//                    msg1[1]=(byte)0x03;
//                    msg1[2]=(byte)0x01;
//                    msg1[3]=(byte)0x92;
//                    msg1[4]=(byte)0xCA;
//                    msg2[0]=(byte)0xA0;
//                    msg2[1]=(byte)0x04;
//                    msg2[2]=(byte)0x01;
//                    msg2[3]=(byte)0x80;
//                    msg2[4]=(byte)0xFF;
//                    msg2[5]=(byte)0xDC;
//                    msg3[0]=(byte)0xA0;
//                    msg3[1]=(byte)0x03;
//                    msg3[2]=(byte)0x01;
//                    msg3[3]=(byte)0x90;
//                    msg3[4]=(byte)0xCC;
//
//
//
//                    //  byte[] bS=edit_send.getText().toString().getBytes();
//                    bS=edit_send.getText().toString();
//
//                    //    bytesToHexFun1(bytes);
//
//                    System.out.println("11111"+sb);
//
//                    //  writer.write(String.valueOf(bytes));
//
//                    toHexString(sb);
//
//
//                        OutputStream socketWriter = socket.getOutputStream();
//                          socketWriter.write(msg2);
//                      socketWriter.flush();
//
//                    //     writer.write(ByteUtil.BinaryToHexString(bS));
////        //    writer.write(String.valueOf(msg));
////           // writer.write(String.valueOf(sb));
//
//
//
//                    //  writer.flush();
//
//
//
//
//
//                    /* 更新UI */
//                    //        edit_send.setText("");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//    public void send3() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    /* 向输出流写数据 */
//
//
//                    //    byte[] test=new byte[]{(byte)0xA0,(byte)0x03,(byte)0x01,(byte)0x72,(byte)0xEA};
//                    //    byte[] test=hexStringToBytes
//                    msg[0]=(byte)0xA0;
//                    msg[1]=(byte)0x03;
//                    msg[2]=(byte)0x01;
//                    msg[3]=(byte)0x72;
//                    msg[4]=(byte)0xEA;
//                    msg1[0]=(byte)0xA0;
//                    msg1[1]=(byte)0x03;
//                    msg1[2]=(byte)0x01;
//                    msg1[3]=(byte)0x92;
//                    msg1[4]=(byte)0xCA;
//                    msg2[0]=(byte)0xA0;
//                    msg2[1]=(byte)0x04;
//                    msg2[2]=(byte)0x01;
//                    msg2[3]=(byte)0x80;
//                    msg2[4]=(byte)0xFF;
//                    msg2[5]=(byte)0xDC;
//                    msg3[0]=(byte)0xA0;
//                    msg3[1]=(byte)0x03;
//                    msg3[2]=(byte)0x01;
//                    msg3[3]=(byte)0x90;
//                    msg3[4]=(byte)0xCC;
//
//
//
//                    //  byte[] bS=edit_send.getText().toString().getBytes();
//                    bS=edit_send.getText().toString();
//
//                    //    bytesToHexFun1(bytes);
//
//                    System.out.println("11111"+sb);
//
//                    //  writer.write(String.valueOf(bytes));
//
//                    toHexString(sb);
//
//
//                        OutputStream socketWriter = socket.getOutputStream();
//                          socketWriter.write(msg3);
//                      socketWriter.flush();
//
//                    //     writer.write(ByteUtil.BinaryToHexString(bS));
////        //    writer.write(String.valueOf(msg));
////           // writer.write(String.valueOf(sb));
//
//
//
//                    //  writer.flush();
//
//
//
//
//
//                    /* 更新UI */
//                    //        edit_send.setText("");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//











    @Override
    protected void onDestroy() {
        try {
            /* 关闭socket */
            if(null != socket){
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.getInputStream().close();
                socket.getOutputStream().close();
                socket.close();
            }
        } catch (IOException e) {
            Log.d(TAG,e.getMessage());
        }
        /* 更新UI */
        btn_connect.setText("连接");
        edit_recv.setText("");
        super.onDestroy();
    }
}
