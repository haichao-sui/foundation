package org.qinarmy.foundation.eamil;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.qinarmy.army.util.Triple;
import org.qinarmy.foundation.util.JsonUtils;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * created  on 2018/10/18.
 */
public class EmailForm {


    /**
     * 必须以 {@link .email} 结尾. 程序会自动加上以下后缀,并从 {@link org.springframework.core.env.Environment} 中获取
     * 值 .
     * <ul>
     * <li>{@code .to}</li>
     * <li>{@code .cc}</li>
     * <li>{@code .bcc}</li>
     * <li>{@code .off.duration}</li>
     * </ul>
     * <p>
     * 例: prefix = "*.email"
     * 则自动搜索 以下值
     * <ul>
     * <li>{@code *.email.to}</li>
     * <li>{@code *.email.cc}</li>
     * <li>{@code *.email.bcc}</li>
     * <li>{@code *.email.off.duration}</li>
     * </ul>
     * </p>
     */
    @Nullable
    private String prefix;


    @Nullable
    private LocalDateTime dateTime;

    @Nullable
    private String subject;


    /**
     * 邮件的主要内容
     */
    @NonNull
    private String text;


    /**
     * 会被打印在邮件中
     */
    @Nullable
    @JsonIgnore
    private Throwable e;

    /**
     * 要发送的附件
     * <p>
     * <ol>
     * <li> first 表示 文件名(若是中文不能过长于10 个字符), 注意 first 不能超过 20 个字符,否则附件不可下载.</li>
     * <li> second 表示 资源位置
     * <ul>
     * <li>{@code classpath:} 即扫本模块的 jar </li>
     * <li>{@code classpath*:} 即扫瞄路径中的所有 jar,注意这里只取第一个资源</li>
     * <li>{@code file:} 文件系统</li>
     * <li>{@code env:} {@link org.springframework.core.env.Environment} 中读取</li>
     * </ul>
     * </li>
     * <li> third 表示 文件类型</li>
     * </ol>
     * </p>
     */
    @Nullable
    private List<Triple<String, String, MediaType>> attachmentList;


    private String exceptionMessage;


    @NonNull
    public String getPrefix() {
        return prefix;
    }

    public EmailForm setPrefix(@NonNull String prefix) {
        this.prefix = prefix;
        return this;
    }


    @Nullable
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public EmailForm setDateTime(@Nullable LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    @Nullable
    public String getSubject() {
        return subject;
    }

    public EmailForm setSubject(@Nullable String subject) {
        this.subject = subject;
        return this;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public EmailForm setText(@NonNull String text) {
        this.text = text;
        return this;
    }

    @Nullable
    public Throwable getE() {
        return e;
    }

    public EmailForm setE(@Nullable Throwable e) {
        this.e = e;
        return this;
    }



    /**
     * 仅限内部实现使用
     */
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Nullable
    public List<Triple<String, String, MediaType>> getAttachmentList() {
        return attachmentList;
    }

    public EmailForm setAttachmentList(@Nullable List<Triple<String, String, MediaType>> attachmentList) {
        this.attachmentList = attachmentList;
        return this;
    }

    @Override
    public String toString() {
        return JsonUtils.writeToJson( this, true );
    }
}
