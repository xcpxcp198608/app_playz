package com.wiatec.playz.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * channel info
 */

public class ChannelInfo implements Parcelable {

    //primary key
    private int id;
    //channel id
    private int channelId;
    //sequence
    private int sequence;
    //query index
    private String tag;
    //channel name
    private String name;
    //stream url
    private String url;
    //channel icon
    private String icon;
    //channel type
    private String type;
    //the country of channel
    private String country;
    //the style of channel
    private String style;
    //visible (0 - gone , 1 - visible)
    private short visible;
    private boolean locked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public short getVisible() {
        return visible;
    }

    public void setVisible(short visible) {
        this.visible = visible;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "ChannelInfo{" +
                "id=" + id +
                ", channelId=" + channelId +
                ", sequence=" + sequence +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", style='" + style + '\'' +
                ", visible=" + visible +
                ", locked=" + locked +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.channelId);
        dest.writeInt(this.sequence);
        dest.writeString(this.tag);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.icon);
        dest.writeString(this.type);
        dest.writeString(this.country);
        dest.writeString(this.style);
        dest.writeInt(this.visible);
        dest.writeByte(this.locked ? (byte) 1 : (byte) 0);
    }

    public ChannelInfo() {
    }

    protected ChannelInfo(Parcel in) {
        this.id = in.readInt();
        this.channelId = in.readInt();
        this.sequence = in.readInt();
        this.tag = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.icon = in.readString();
        this.type = in.readString();
        this.country = in.readString();
        this.style = in.readString();
        this.visible = (short) in.readInt();
        this.locked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ChannelInfo> CREATOR = new Parcelable.Creator<ChannelInfo>() {
        @Override
        public ChannelInfo createFromParcel(Parcel source) {
            return new ChannelInfo(source);
        }

        @Override
        public ChannelInfo[] newArray(int size) {
            return new ChannelInfo[size];
        }
    };
}
