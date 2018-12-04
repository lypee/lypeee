package com.lypee.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class EmailUtil {
    private static String defaultSenderName ="" ;
    private static String defaultSenderPass = "" ;
    private static String defaultSmtpHost="" ;//默认服务器地址
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static String smtpHost ; //邮件服务器地址
    private static String sendUserName ;
    private static String sendUserPass ;

    private static MimeMessage mimeMsg ;
    private static Session session ;
    private static Properties props ;
    private static Multipart mp ;
    private static List<FileDataSource> files = new LinkedList<>() ;

    public static String sendEmail(String emailTo)
    {
        String userName="m3251789@163.com" ;
        String password="lpy980719" ;
        String smtpHost = "smtp.163.com" ;
        String subject="激活码" ;
        String activationCode = activationCode();
        String body = "这是你的激活码 三小时内有效" ;
        EmailUtil email = EmailUtil.entity(smtpHost,userName,password,emailTo,subject,body);
        try
        {
            email.send() ;
        }catch (Exception e)
        {

        }
        return activationCode ;
    }
    private void init()
    {
        if(props == null)
        {
         props = System.getProperties() ;
        }
        session=  Session.getDefaultInstance(props ,null) ;
        props.setProperty("mail.smtp.host" , smtpHost);
        props.setProperty("mail.smtp.socketFactory.class" ,SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback" ,"false");
        //邮件发送端口
        props.setProperty("mail.smtp.port","465");
        props.setProperty("mail.smtp.socketFactory.prot","465");
        props.put("main.smtp.auth","true");
        /**
         * 控制台上看见发送邮件的过
         *程
         */
        session.setDebug(true);
        mimeMsg = new MimeMessage(session) ;
        mp = new MimeMultipart();
    }
    /**
     * 没有抄送人和附件
     */
    private EmailUtil(String smtpHost, String sendUserName, String sendUserPass, String to, String mailSubject, String mailBody) {
        EmailUtil.smtpHost = smtpHost;
        EmailUtil.sendUserName = sendUserName;
        EmailUtil.sendUserPass = sendUserPass;

        init();
        setFrom(sendUserName);
        setTo(to);
        setBody(mailBody);
        setSubject(mailSubject);
    }
    /**
     * 有抄送人和附件
     */
    private EmailUtil(String smtpHost, String sendUserName, String sendUserPass, String to, String cc, String mailSubject, String mailBody,
                      List<String> attachments) {
        EmailUtil.smtpHost = smtpHost;
        EmailUtil.sendUserName = sendUserName;
        EmailUtil.sendUserPass = sendUserPass;

        init();
        setFrom(sendUserName);
        setTo(to);
        setCC(cc);
        setBody(mailBody);
        setSubject(mailSubject);
        if (attachments != null) {
            for (String attachment : attachments) {
                addFileAffix(attachment);
            }
        }
    }

    /**
     * 邮件实体
     *
     * @param smtpHost
     *            邮件服务器地址
     * @param sendUserName
     *            发件邮件地址
     * @param sendUserPass
     *            发件邮箱密码
     * @param to
     *            收件人，多个邮箱地址以半角逗号分隔
     * @param cc
     *            抄送，多个邮箱地址以半角逗号分隔
     * @param mailSubject
     *            邮件主题
     * @param mailBody
     *            邮件正文
    // * @param attachmentPath
     *            附件路径
     * @return
     */
    public static EmailUtil entity(String smtpHost, String sendUserName, String sendUserPass, String to, String cc, String mailSubject, String mailBody,
                                   List<String> attachments) {
        return new EmailUtil(smtpHost, sendUserName, sendUserPass, to, cc, mailSubject, mailBody, attachments);
    }

    /**
     * 邮件实体
     *
     * @param smtpHost
     *            邮件服务器地址
     * @param sendUserName
     *            发件邮件地址
     * @param sendUserPass
     *            发件邮箱密码
     * @param to
     *            收件人，多个邮箱地址以半角逗号分隔
     * @param mailSubject
     *            邮件主题
     * @param mailBody
     *            邮件正文
     * @return
     */
    public static EmailUtil entity(String smtpHost, String sendUserName, String sendUserPass, String to, String mailSubject, String mailBody) {
        return new EmailUtil(smtpHost, sendUserName, sendUserPass, to, mailSubject, mailBody);
    }

    /**
     * 默认邮件实体，用了默认的发送帐号和邮件服务器
     *
     * @param to
     *            收件人，多个邮箱地址以半角逗号分隔
     * @param cc
     *            抄送，多个邮箱地址以半角逗号分隔
     * @param subject
     *            邮件主题
     * @param body
     *            邮件正文
  //   * @param attachment
     *            附件全路径
     * @return
     */
    public static EmailUtil defaultEntity(String to, String cc, String subject, String body, List<String> attachments) {
        return new EmailUtil(defaultSmtpHost, defaultSenderName, defaultSenderPass, to, cc, subject, body, attachments);
    }
    private boolean setSubject(String mailSubject)
    {
        try
        {
            mimeMsg.setSubject(mailSubject);
        }catch (Exception e)
        {
            e.printStackTrace();
            return false ;
        }
        return true ;
    }
    /**
     * 设置邮件内容和格式
     */
    private boolean setBody(String mainBody)
    {
        try
        {
            BodyPart bodyPart = new MimeBodyPart() ;
            bodyPart.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>" + bodyPart, "text/html;charset=UTF-8");
            // 在组件上添加邮件文本
            mp.addBodyPart(bodyPart);
        } catch (Exception e) {
            System.err.println("设置邮件正文时发生错误！" + e);
            return false;
        }
        return true;
        }
    /**
     * 添加附件
     */
    public boolean addFileAffix(String filename)
    {
        try
        {
            if(filename != null && filename.length() > 0)
            {
                BodyPart bodyPart = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(filename) ;
                bodyPart.setDataHandler(new DataHandler(fileDataSource));
                bodyPart.setFileName(MimeUtility.encodeText(fileDataSource.getName(),"utf-8", null)); // 解决附件名称乱码
                mp.addBodyPart(bodyPart);// 添加附件
                files.add(fileDataSource);
            }
        }catch (Exception e)
        {
            System.err.println("新增邮件失败");
            return false ;
        }
        return true ;
    }
    /**
     * 删除所有附件
     *
     * @return
     */
    public boolean delFileAffix() {
        try {
            FileDataSource fileds = null;
            for (Iterator<FileDataSource> it = files.iterator(); it.hasNext();) {
                fileds = it.next();
                if (fileds != null && fileds.getFile() != null) {
                    fileds.getFile().delete();
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置发件人地址
     *
     * @param from
     *            发件人地址
     * @return
     */
    private boolean setFrom(String from) {
        try {
            mimeMsg.setFrom(new InternetAddress(from));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置收件人地址
     *
     * @param to收件人的地址
     * @return
     */
    private boolean setTo(String to) {
        if (to == null)
            return false;
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置抄送
     *
     * @param cc
     * @return
     */
    private boolean setCC(String cc) {
        if (cc == null) {
            return false;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 发送邮件
     *
     * @return
     */
    public boolean send() throws Exception {
        mimeMsg.setContent(mp);
        mimeMsg.saveChanges();
        System.out.println("正在发送邮件....");
        Transport transport = session.getTransport("smtp");
        // 连接邮件服务器并进行身份验证
        transport.connect(smtpHost, sendUserName, sendUserPass);
        // 发送邮件
        transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
        System.out.println("发送邮件成功！");
        transport.close();
        return true;
    }

    // 生成6位随机激活码
    public static String activationCode(){
        return (int)((Math.random()*9+1)*100000) + "";
    }
}

