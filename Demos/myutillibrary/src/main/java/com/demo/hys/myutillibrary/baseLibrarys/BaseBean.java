package com.demo.hys.myutillibrary.baseLibrarys;

/**
 * Create By Hys ${Date}
 */
public class BaseBean<T> {

  private String ret;
  private String code;
  private T dataBean;

  public String getRet() {
    return ret;
  }

  public void setRet(String ret) {
    this.ret = ret;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public T getDataBean() {
    return dataBean;
  }

  public void setDataBean(T dataBean) {
    this.dataBean = dataBean;
  }
}
