package retrofit2.converter.gson;

import com.demo.hys.myutillibrary.Utils.Encryptions;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class CustomConverterFactory extends Converter.Factory {
  private final Gson gson;
  private final Encryptions encryptions;

  public static CustomConverterFactory create(Encryptions encryptions) {
    return create(new Gson(),encryptions);
  }

  @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
  public static CustomConverterFactory create(Gson gson,Encryptions encryptions) {
    if (gson == null) throw new NullPointerException("gson == null");
    return new CustomConverterFactory(gson,encryptions);
  }

  private CustomConverterFactory(Gson gson,Encryptions encryptions) {
    this.gson = gson;
    this.encryptions = encryptions;
  }

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
    TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
    return new CustomConvertResponse<>(adapter,gson,encryptions);
//    return new CustomGsonResponseConverter<>(gson, adapter);
  }

  @Override
  public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
    TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
    return new GsonRequestBodyConverter<>(gson, adapter);
  }
}
