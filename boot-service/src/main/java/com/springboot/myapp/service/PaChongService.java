package com.springboot.myapp.service;

import com.google.common.collect.Sets;
import com.springboot.base.utils.FileUtils;
import com.springboot.base.utils.HttpDownload;
import com.springboot.base.utils.HttpUtil;
import com.springboot.base.utils.LogUtils;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zengJian on 2018/6/2<br>
 * <br>
 */
@Service
public class PaChongService {

    private String targetPage = "";
    private boolean stop = false;

    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private String regex = "src=\"(.+?)\"";
    private Pattern pattern = Pattern.compile(regex);

    public void startTast(){
        stop = false;
    }

    public void stopTask(){
        stop = true;
    }

    public String getTargetPage(){

        return targetPage;
    }

    public void setTargetPage(String str){
        this.targetPage = str;
    }

    public String getDocument(String url){
        return "";
    }

    public String getVideoUrl(Object videoDom){
        return videoDom.toString();
    }

    public void run(){
        LogUtils.warnPrint("开始下载--------");
        int page = 0;
        int maxPage = 0;
        int pageSize = 50;
        String name = "fuckxiaoyuan99";
        int startNum = getStartNum(page,pageSize);
        while(true){
            AtomicInteger videoIndex = new AtomicInteger();
            String url = "http://"+name+".tumblr.com/api/read?type=video&num="+pageSize+"&start="+startNum;
            String response = HttpUtil.sendGet(url, null);
            LogUtils.warnPrint(String.format("startNum[%d],返回值：[%s]",startNum,response));
            List<String> videoUrls = getVideoUrls(response);
            if(CollectionUtils.isEmpty(videoUrls)){
                break;
            }
            videoUrls.stream().forEach(videoUrl->{
                videoIndex.getAndDecrement();
                String filePath = "/data/files/"+videoIndex.toString()+".mp4";
                LogUtils.warnPrint("开始下载文件："+filePath);
                HttpDownload.download(videoUrl,filePath);
            });
        }
    }

    public int getStartNum(int page,int pageSize){
        return page*pageSize;
    }

    public List<String> getVideoUrls(String response){
        try {
            Set<String> urls = Sets.newHashSet();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document document = db.parse(new File(response));
            Document document = db.parse(response);
            NodeList videoPlayer = document.getElementsByTagName("video-player");
            int length = videoPlayer.getLength();
            if(length == 0)
                return null;
            for (int i = 0; i < videoPlayer.getLength(); i++) {
                Node item = videoPlayer.item(i);
                Matcher matcher = pattern.matcher(item.getTextContent());
                if(matcher.find()){
                    String group = matcher.group();
                    String substring = group.substring(5, group.length() - 1);
                    urls.add(substring);
                }
            }
            return Lists.newArrayList(urls);
        } catch (Exception e) {
            LogUtils.errorPrint("获取reponse中的url错误",e);
        }
        return null;
    }

}