package retrofit2.converter.gson;

import android.util.Log;
import com.demo.hys.myutillibrary.Utils.Encryptions;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Converter;

public class CustomConvertResponse<T> implements Converter<ResponseBody, T> {

  private final TypeAdapter<T> typeAdapter;
  private final Gson gson;
  private static final String TAG = "CustomConvertResponse";
  private Encryptions encryptions;

  public CustomConvertResponse(TypeAdapter<T> typeAdapter, Gson gson,Encryptions encryptions) {
    this.typeAdapter = typeAdapter;
    this.gson = gson;
    this.encryptions = encryptions;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    String originalBody = "";
    try {
      originalBody = value.string();
      Log.i(TAG, "convert: "+originalBody);
      JSONObject json = new JSONObject(originalBody);
      String data = json.getString("data");
      String code = json.getString("code");
      String isNull = "0";
      if (code.equals("1001")) {
        try {
          isNull = json.getString("is_null");
        } catch (JSONException e) {
        }
        if(isNull.equals("0")) {
          String dS =encryptions.decrpy(data);
          Log.i(TAG, "convert: "+dS);
          return typeAdapter.fromJson(dS);
        }
        } else {
        Log.i("POST", "数据获取失败");
        throw new Exception("code="+code);
      }
    } catch (JSONException e) {
      e.printStackTrace();
      Log.i("JSON", "解析失败"+originalBody);
    } catch (Exception e) {
      e.printStackTrace();
      Log.i("自定义Convert", "解密失败");
    } finally {
      value.close();
    }
    return null;
  }
}
