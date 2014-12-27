package com.sting2me.dataserver;

import java.util.Random;
import java.util.UUID;

/**
 * Created by peter on 14-12-10.
 */
public class PathGenerator {
    private static int MAX_FOLDER_LEVEL = 3;
    //TODO These value should come from config file
    private static String ROOT_DEVICE = "/tmp/";
    private static int LEVEL_1 = 100;
    private static double D_LEVEL_1 = LEVEL_1;
    private static int LEVEL_2 = 100;
    private static double D_LEVEL_2 = LEVEL_2;
    private static int LEVEL_2_TOTAL = LEVEL_1 * LEVEL_2;
    private static int LEVEL_3 = 10;  ///total folder == 100*100*10 + 100*100 + 100= 11.1万
    private static double D_LEVEL_3 = LEVEL_3;
    private static int LEVEL_3_TOTAL = LEVEL_1 * LEVEL_2 * LEVEL_3;
    private static int totalFolder = LEVEL_1 * LEVEL_2 * LEVEL_3 + LEVEL_1 * LEVEL_2 + LEVEL_1;
    public static String getPath() {
        String uuid = UUID.randomUUID().toString();
        StringBuffer buff = new StringBuffer();
        buff.append(ROOT_DEVICE);
        buff.append(getRandomizePath());
        return buff.toString();
    }
    //for image
    public static String getPath(int width, int height) {
        String uuid = UUID.randomUUID().toString();
        StringBuffer buff = new StringBuffer();
        buff.append(ROOT_DEVICE);
        buff.append(getRandomizePath());
        buff.append(uuid);
        buff.append("_w" + width + "_h" + height);
        return buff.toString();
    }
    // for audio/video
    public static String getPath(int length) {
        String uuid = UUID.randomUUID().toString();
        StringBuffer buff = new StringBuffer();
        buff.append(ROOT_DEVICE);
        buff.append(getRandomizePath());
        buff.append(uuid);
        buff.append("_l" + length);
        return buff.toString();
    }
    //for video
    public static String getPath(int width, int height, int length) {
        String uuid = UUID.randomUUID().toString();
        StringBuffer buff = new StringBuffer();
        buff.append(ROOT_DEVICE);
        buff.append(getRandomizePath());
        buff.append(uuid);
        buff.append("_w" + width + "_h" + height + "_l" + length);
        return buff.toString();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    private static String getRandomizePath() {
        Random r  = new Random();
        int seed = r.nextInt(totalFolder);
        seed += 1;
        System.out.println("seed:" + seed);
        StringBuffer sb = new StringBuffer();
        sb.append("/");
        if (seed <= LEVEL_1) {// fall in L1
            sb.append(seed);
        } else if (seed <= LEVEL_2_TOTAL + LEVEL_1) { //fall in L2
            sb.append(Math.ceil((seed - LEVEL_1) / D_LEVEL_2) );
            sb.append("/");
            int n = (seed - LEVEL_1) % LEVEL_2;
            if (n == 0)
                sb.append(LEVEL_2);
            else sb.append(n);
        } else { //fall in L3
            int l3 = (seed - LEVEL_2_TOTAL- LEVEL_1) % LEVEL_3  ;
            if(l3 == 0 )
                l3 = LEVEL_3;

            double l2 = Math.ceil((seed - LEVEL_2_TOTAL- LEVEL_1) / D_LEVEL_3 ) % LEVEL_2;
            if(l2 == 0)
                l2 = LEVEL_2;
            double l1 = Math.ceil((seed - LEVEL_2_TOTAL - LEVEL_1) / D_LEVEL_3 / D_LEVEL_2) % LEVEL_1;
            if(l1 == 0)
                l1 = LEVEL_1;
            sb.append(l1);
            sb.append("/");

            sb.append(l2);
            sb.append("/");

            sb.append(l3);
        }
        sb.append("/");
        return sb.toString().replace(".0", "");
    }

    public static void main(String[] args) {
        System.out.println(getRandomizePath());
        System.out.println(Math.ceil(0.01));
    }
}
