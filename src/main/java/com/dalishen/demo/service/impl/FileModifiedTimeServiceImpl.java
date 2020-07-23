package com.dalishen.demo.service.impl;

import com.dalishen.demo.bean.FileModifiedTimeBean;
import com.dalishen.demo.bean.OpLog;
import com.dalishen.demo.mapper.FileModifiedTimeMapper;
import com.dalishen.demo.mapper.ManageMapper;
import com.dalishen.demo.service.FileModifiedTimeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Slf4j
public class FileModifiedTimeServiceImpl implements FileModifiedTimeService {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Autowired
    private FileModifiedTimeMapper fileModifiedTimeMapper;

    @Autowired
    private ManageMapper manageMapper;

    /**
     * 将bean存储到 Mysql
     * @param fileModifiedTimeBeanList
     * @param creator
     * @return
     */
    @Override
    public Long save2Mysql(List<FileModifiedTimeBean> fileModifiedTimeBeanList, String creator) {
        fileModifiedTimeBeanList.stream().forEach(fileModifiedTimeBean -> {
            fileModifiedTimeBean.setCreator(creator);
            fileModifiedTimeBean.setCreateTime(new Date());
        });

        Long insertNumber = fileModifiedTimeMapper.batchInsert(fileModifiedTimeBeanList);
        return insertNumber;
    }



    @Override
    public Map selectFileModifiedTimeBeanByFileName(Long logId, String filename){

        Map resultMap = new HashMap();
        FileModifiedTimeBean fileModifiedTimeBean = fileModifiedTimeMapper.selectFileModifiedTimeBeanByFileName(filename);
        OpLog opLog = manageMapper.selectOpLogById(logId);
        log.info("selectFileModifiedTimeBeanByFileName, logId: [{}], filename: [{}]",
                logId, filename);

        resultMap.put("logid", opLog);
        resultMap.put("filename", fileModifiedTimeBean);
        return resultMap;
    }

    @Override
    public FileModifiedTimeBean selectFileModifiedTimeBeanById(Long id){

        FileModifiedTimeBean fileModifiedTimeBean = fileModifiedTimeMapper.selectFileModifiedTimeBeanById(id);
        log.info("selectFileModifiedTimeBeanById, fileModifiedTimeBean: [{}]", fileModifiedTimeBean.toString());
        return fileModifiedTimeBean;
    }
    @Override
    public List<FileModifiedTimeBean> getFileModifiedTimeBeanFrom(String rootPath) {
        List<FileModifiedTimeBean> beanList = new ArrayList<>();

        File file = new File(rootPath);
//        File file = new File("D:\\work-doc\\ref\\fleaf\\WEB-INF\\classes");
        if (file.isDirectory()) {
            listFiles(file, beanList);
        }

        return beanList;
    }


    public void listFiles(File directory, List<FileModifiedTimeBean> beanList) {

        File[] listFiles = directory.listFiles();

        for (int i = 0; i < listFiles.length; i++) {
            File f = listFiles[i];
            if (f.isDirectory()) {
                listFiles(f, beanList); //存在最终的节点
            } else {
                if (f.getName().endsWith(".class") && !f.getName().contains("$")) {
                    System.out.println(f.getAbsolutePath() + "   " +
                            getTime(f.lastModified()));
                    FileModifiedTimeBean bean = new FileModifiedTimeBean();
                    bean.setFilePath(f.getPath());
                    bean.setFileName(f.getName());
                    bean.setModifiedTime(new Date(f.lastModified()));
                    beanList.add(bean);
                }
            }
        }



    }



    public static String getTime(long time) {
        Date d = new Date(time);
        return format.format(d);
    }

}
