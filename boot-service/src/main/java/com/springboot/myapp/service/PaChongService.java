package com.springboot.myapp.service;

import com.google.common.collect.Sets;
import com.springboot.base.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
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

    public void run(String name) throws Exception{
        if(StringUtils.isBlank(name))
            throw new NullPointerException();

        LogUtils.warnPrint(String.format("开始下载--------%s", name));
        int page = 0;
        int pageSize = 50;
        AtomicInteger videoIndex = new AtomicInteger();
        while(true){
            int startNum = getStartNum(page,pageSize);
            String url = "http://"+name+".tumblr.com/api/read?type=video&num="+pageSize+"&start="+startNum;
            String response = HttpUtil.sendGet(url, null);
            LogUtils.warnPrint(String.format("startNum[%d]-------",startNum));

//            FileUtils.writeToFile("/home/files/raw/"+name+"/"+startNum+".xml",response,false);

            List<String> videoUrls = getVideoUrls(response);
            if(CollectionUtils.isEmpty(videoUrls)){
                break;
            }
            videoUrls.stream().forEach(videoUrl->{
                videoIndex.getAndIncrement();
                String filePath = "/home/files/"+name+"/"+videoIndex.toString()+".mp4";
                LogUtils.warnPrint("开始下载文件："+filePath);
                HttpDownload.download(videoUrl,filePath);
            });
            page++;
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
            InputSource inputSource = new InputSource(new ByteArrayInputStream(response.getBytes("utf-8")));
            Document document = db.parse(inputSource);
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
