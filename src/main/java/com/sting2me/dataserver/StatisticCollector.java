package com.sting2me.dataserver;

import com.sting2me.common.util.CommandExecutor;
import com.sting2me.common.util.CommandResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by peter on 14-12-10.
 */
public class StatisticCollector {
    private final String DF_USAGE = "df --output=source,pcent";
    private final String DF_INODE = "df --output=source,ipcent";
    private final String DF_ALL = "df --output=source,pcent,ipcent";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        StatisticCollector s = new StatisticCollector();
        s.getDiskUsage("/dev/sda9");
        s.getInodeUsage("/dev/sda9");
        s.getAll("/dev/sda9");
    }

    public Map<String, Double> getDiskUsage(final String partition) {
        CommandResult cr = CommandExecutor.execute(DF_USAGE + " " + partition);
        String output = cr.getOutput();
        Pattern pattern = Pattern.compile(partition + "\\s+(\\d+)%");
        Matcher m = pattern.matcher(output);
        Map<String, Double> data = new HashMap<String, Double>(1);
        if (m.find()) {
            data.put(partition, new Double(m.group(1)));
        }
        return data;
    }

    public Map<String, Double> getInodeUsage(final String partition) {
        CommandResult cr = CommandExecutor.execute(DF_INODE + " " + partition);
        String output = cr.getOutput();
        Pattern pattern = Pattern.compile(partition + "\\s+(\\d+)%");
        Matcher m = pattern.matcher(output);
        Map<String, Double> data = new HashMap<String, Double>(1);
        if (m.find()) {
            data.put(partition, new Double(m.group(1)));
        }
        return data;

    }

    public Map<String, Double[]> getAll(final String partition) {
        CommandResult cr = CommandExecutor.execute(DF_ALL + " " + partition);
        String output = cr.getOutput();
        Pattern pattern = Pattern.compile(partition + "\\s+(\\d+)%\\s+(\\d+)%");
        Matcher m = pattern.matcher(output);
        Map<String, Double[]> data = new HashMap<String, Double[]>(1);
        if (m.find()) {
            data.put(partition, new Double[]{new Double(m.group(1)), new Double(m.group(2))});
        }
        return data;
    }
}
