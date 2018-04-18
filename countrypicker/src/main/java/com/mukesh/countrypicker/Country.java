package com.mukesh.countrypicker;

import android.content.Context;
import android.text.TextUtils;
import java.util.Locale;

public class Country {

  // region Variables
  private String code;
  private String name;
  private String dialCode;
  private int flag;
  private String currency;
  // endregion

  // region Constructors
  Country() {
  }

  Country(String code, String name, String dialCode, int flag, String currency) {
    this.code = code;
    this.name = name;
    this.dialCode = dialCode;
    this.flag = flag;
    this.currency = currency;
  }

  Country(Builder builder) {
    this.code = builder.code;
    this.name = builder.name;
    this.dialCode = builder.dialCode;
    this.flag = builder.flag;
    this.currency = builder.currency;
  }
  // endregion

  public static class Builder {
    private String code;
    private String name;
    private String dialCode = "";
    private int flag = 0;
    private String currency = "";

    public Builder(String code, String name) {
      this.code = code;
      this.name = name;
    }

    public Builder setDialCode(String dialCode) {
      this.dialCode = dialCode;
      return this;
    }

    public Builder setFlag(int flag) {
      this.flag = flag;
      return this;
    }

    public Builder setCurrency(String currency) {
      this.currency = currency;
      return this;
    }

    public Country build() {
      return new Country(this);
    }
  }

  // region Getter/Setter
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
    if (TextUtils.isEmpty(name)) {
      name = new Locale("", code).getDisplayName();
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDialCode() {
    return dialCode;
  }

  public void setDialCode(String dialCode) {
    this.dialCode = dialCode;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  public void loadFlagByCode(Context context) {
    if (this.flag != -1) {
      return;
    }

    try {
      this.flag = context.getResources()
          .getIdentifier("flag_" + this.code.toLowerCase(Locale.ENGLISH), "drawable",
              context.getPackageName());
    } catch (Exception e) {
      e.printStackTrace();
      this.flag = -1;
    }
  }
  // endregion
}
