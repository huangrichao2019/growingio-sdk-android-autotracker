import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by lishaojie on 2016/12/13.
 */

public class TestMessageHandler extends Handler {
    private static final String TAG = "TestHandler";
    private LinkedList<Message> uploadQueue;
    private Deque<Message> allMessageQueue;
    private LinkedList<Message> dbSaveQueue;
    private Context mContext;
    private final static String LOG_FILE_NAME = "gio_msg.txt";
    private final String messageIn = "采集数据进入队列";
    @NonNull
    private ArrayList<Pair<Deque<Message>, List<Integer>>> filteredMessageQueues = new ArrayList<>();

    public static int MP_TEST_SAVE_EVENT = 0x100000;
    public static int DB_TEST_MSG_FLAG = 0x200000;
    public static int DB_TEST_CREATE_DB = DB_TEST_MSG_FLAG + 1;
    public static int DB_TEST_UPGRADE_DB = DB_TEST_CREATE_DB + 1;
    public static int DB_TEST_SAVE_EVENT = DB_TEST_UPGRADE_DB + 1;
    public static int DB_TEST_CLEAN_EVENT = DB_TEST_SAVE_EVENT + 1;
    public static int DB_TEST_READ_DB = DB_TEST_CLEAN_EVENT + 1;
    public static int MU_TEST_NEW_EVENT_SAVED = 0x400000;
    public static int MU_TEST_UPLOAD_EVENT = MU_TEST_NEW_EVENT_SAVED + 1;
    public static int CONFIG_TEST_SAVE_SERVER_SETTINGS = 0x80000;
    public static int CONFIG_TEST_DEVICE_ACTIVATED = CONFIG_TEST_SAVE_SERVER_SETTINGS + 1;

    public LinkedList<Message> getUploadQueue() {
        return uploadQueue;
    }

    public Deque<Message> getAllMessageQueue() {
        return allMessageQueue;
    }

    public LinkedList<Message> getDbSaveQueue() {
        return dbSaveQueue;
    }

    TestMessageHandler(Context context) {
        mContext = context;
        allMessageQueue = new ConcurrentLinkedDeque<Message>() {
            @Override
            public boolean add(Message msg) {

                JSONObject mEvent = (JSONObject) ((Object[]) msg.obj)[0];
                Log.i(messageIn, mEvent.toString());

                String result = "handleMessage: " + REVERSE_MAP.get(msg.what) + " @ | ";
                if (msg.obj instanceof Object[]) {
                    for (Object o : (Object[]) msg.obj) {
                        result += o.toString() + " | ";
                    }
                }
                try {
                    FileOutputStream stream = mContext.openFileOutput(LOG_FILE_NAME, Context.MODE_APPEND);
                    stream.write((result + "\n").getBytes());
                    stream.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                return super.add(msg);
            }
        };
        registerMessageQueue(allMessageQueue, Collections.singletonList(MP_TEST_SAVE_EVENT));
        context.getFileStreamPath(LOG_FILE_NAME).delete();
//        dbSaveQueue = new LinkedList<>();
//        registerMessageQueue(dbSaveQueue, Collections.singletonList(DB_TEST_SAVE_EVENT));
//        uploadQueue = new LinkedList<>();
//        registerMessageQueue(uploadQueue, Collections.singletonList(MU_TEST_UPLOAD_EVENT));
    }

    public void registerMessageQueue(Deque<Message> queue, List<Integer> filter) {
        filteredMessageQueues.add(new Pair<>(queue, filter));
    }

    @NonNull
    private static SparseArray<String> REVERSE_MAP = new SparseArray<>();

    static {
        Field[] fields = TestMessageHandler.class.getDeclaredFields();
        for (Field f : fields) {
            if (f.getType() == int.class && Modifier.isStatic(f.getModifiers())) {
                try {
                    REVERSE_MAP.put(f.getInt(null), f.getName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        for (Pair<Deque<Message>, List<Integer>> queue : filteredMessageQueues) {
            List<Integer> filter = queue.second;
            if (filter == null || filter.contains(msg.what)) {
                queue.first.add(Message.obtain(msg));
            }
        }
    }
}
