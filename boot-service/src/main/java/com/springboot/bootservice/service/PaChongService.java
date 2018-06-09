package com.springboot.bootservice.service;

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
    private static boolean TEST = false;
    private String regex = "src=\"(.+?)\"";
    private Pattern pattern = Pattern.compile(regex);

    public void run(String name) throws Exception{
        if(StringUtils.isBlank(name))
            throw new NullPointerException();

        LogUtils.warnPrint(String.format("开始下载--------%s", name));
        int page = 0;
        int pageSize = 50;
        AtomicInteger videoIndex = new AtomicInteger();
        while(true){
            int startNum = getStartNum(page,pageSize);
            String url = getTargetUrl(name,pageSize,startNum);
            String response = HttpUtil.sendGet(url, null);
            LogUtils.warnPrint(String.format("startNum[%d]-------",startNum));

//            FileUtils.writeToFile("/home/files/raw/"+name+"/"+startNum+".xml",response,false);

            List<String> videoUrls = getVideoUrls(response);
            if(CollectionUtils.isEmpty(videoUrls)){
                break;
            }
            videoUrls.stream().forEach(videoUrl->{
                videoIndex.getAndIncrement();
                String filePath = getFileSavePath(name,videoIndex.toString(),videoUrl);
                LogUtils.warnPrint("开始下载文件："+filePath);
                HttpDownload.download(videoUrl,filePath);
            });
            page++;
        }
    }

    private String getTargetUrl(String name,Integer pageSize,Integer startNum){
        return "http://"+name+".tumblr.com/api/read?num="+pageSize+"&start="+startNum;
    }

    private String getFileSavePath(String name,String index,String url){
        if(url.indexOf(".mp4") > 0){
            return "/home/files/"+name+"/mp4/"+index+".mp4";
        }else if(url.indexOf(".jpg") > 0){
            return "/home/files/"+name+"/jpg/"+index+".jpg";
        }else if(url.indexOf(".gif") > 0){
            return "/home/files/"+name+"/gif/"+index+".gif";
        }
        return "/home/files/"+name+"/"+index+".mp4";
    }


    public int getStartNum(int page,int pageSize){
        return page*pageSize;
    }

    public static void main(String[] args) throws Exception {
        //TEST = true;
        PaChongService paChongService = new PaChongService();
        paChongService.run("nbwangqi");
//        List<String> videoUrls = paChongService.getVideoUrls("G:\\my.xml");
//        System.out.println(videoUrls.size());
//        for (String videoUrl : videoUrls) {
//            System.out.println(videoUrl);
//        }
    }

    public List<String> getVideoUrls(String response){
        try {
            if(StringUtils.isBlank(response)){
                LogUtils.errorPrint("response is empty");
                return null;
            }
            Set<String> urls = Sets.newHashSet();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = null;
            if(TEST){
                document = db.parse(new File(response));
            }else{
                InputSource inputSource = new InputSource(new ByteArrayInputStream(response.getBytes("utf-8")));
                document = db.parse(inputSource);
            }
            NodeList post = document.getElementsByTagName("post");
            int length = post.getLength();
            if(length == 0)
                return null;
            for (int i = 0; i < post.getLength(); i++) {
                Node item = post.item(i);
                NodeList childNodes = item.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node item1 = childNodes.item(j);
                    String nodeName = item1.getNodeName();
                    if("video-player".equals(nodeName)){
                        getContentUrl(urls, item1);
                        break;
                    }else if("photo-url".equals(nodeName)){
                        urls.add(item1.getTextContent());
                        break;
                    }else if("photo-url".equals(nodeName)){
                        urls.add(item1.getTextContent());
                        break;
                    }
                }
            }

//            NodeList videoPlayer = document.getElementsByTagName("video-player");
//            int length = videoPlayer.getLength();
//            if(length == 0)
//                return null;
//            for (int i = 0; i < videoPlayer.getLength(); i++) {
//                Node item = videoPlayer.item(i);
//                Matcher matcher = pattern.matcher(item.getTextContent());
//                if(matcher.find()){
//                    String group = matcher.group();
//                    String substring = group.substring(5, group.length() - 1);
//                    urls.add(substring);
//                }
//            }
             return Lists.newArrayList(urls);
        } catch (Exception e) {
            LogUtils.errorPrint(response);
            LogUtils.errorPrint("获取reponse中的url错误",e);
        }
        return null;
    }

    private void getContentUrl(Set<String> urls, Node item) {
        Matcher matcher = pattern.matcher(item.getTextContent());
        if(matcher.find()){
            String group = matcher.group();
            String substring = group.substring(5, group.length() - 1);
            urls.add(substring);
        }
    }

}
