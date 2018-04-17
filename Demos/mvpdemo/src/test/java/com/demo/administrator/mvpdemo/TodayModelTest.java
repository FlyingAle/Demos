package com.demo.administrator.mvpdemo;

import com.demo.administrator.mvpdemo.Models.TodayModel;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TodayModelTest {

  TodayModel mockModel;

  @Before
  public void setup()
  {
    mockModel = mock(TodayModel.class);
  }

  @Test
  public void getAndroidTest()
  {
    mockModel.getAndroidBean();
    verify(mockModel).getAndroidBean();
  }

  @Test
  public void getIosTest()
  {
    mockModel.getIosBean();
    verify(mockModel).getIosBean();
  }
}
