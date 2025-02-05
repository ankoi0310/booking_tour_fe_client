package vn.edu.hcmuaf.fit.travie.hotel.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.hcmuaf.fit.travie.core.handler.domain.HttpResponse;
import vn.edu.hcmuaf.fit.travie.core.handler.domain.paging.Page;
import vn.edu.hcmuaf.fit.travie.core.service.RetrofitService;
import vn.edu.hcmuaf.fit.travie.core.shared.utils.AppUtil;
import vn.edu.hcmuaf.fit.travie.hotel.data.model.Hotel;
import vn.edu.hcmuaf.fit.travie.hotel.data.service.HotelService;

public class HotelViewModel extends ViewModel {
    private final MutableLiveData<HotelListResult> nearByHotelList = new MutableLiveData<>();
    private final MutableLiveData<HotelListResult> popularHotelList = new MutableLiveData<>();
    private final MutableLiveData<HotelListResult> exploreResult = new MutableLiveData<>();
    private final MutableLiveData<HotelListResult> searchResult = new MutableLiveData<>();
    private final MutableLiveData<HotelResult> hotel = new MutableLiveData<>();

    private final HotelService hotelService;

    public HotelViewModel() {
        this.hotelService = RetrofitService.createPublicService(HotelService.class);
    }

    public LiveData<HotelListResult> getNearByHotelList() {
        return nearByHotelList;
    }

    public LiveData<HotelListResult> getPopularHotelList() {
        return popularHotelList;
    }

    public LiveData<HotelListResult> getExploreResult() {
        return exploreResult;
    }

    public LiveData<HotelListResult> getSearchResult() {
        return searchResult;
    }

    public LiveData<HotelResult> getHotel() {
        return hotel;
    }

    public void fetchNearByHotelList(String location, Integer page, Integer size) {
        hotelService.getNearByHotelList(location, page, size).enqueue(new Callback<HttpResponse<Page<Hotel>>>() {
            @Override
            public void onResponse(@NonNull Call<HttpResponse<Page<Hotel>>> call, @NonNull Response<HttpResponse<Page<Hotel>>> response) {
                try (ResponseBody errorBody = response.errorBody()) {
                    if (!response.isSuccessful() && errorBody != null) {
                        Gson gson = AppUtil.getGson();
                        Type type = new TypeToken<HttpResponse<String>>() {
                        }.getType();
                        HttpResponse<String> httpResponse = gson.fromJson(errorBody.charStream(), type);
                        exploreResult.postValue(new HotelListResult(null, httpResponse.getMessage()));
                        return;
                    }

                    if (response.body() == null) {
                        exploreResult.postValue(new HotelListResult(null, "Something went wrong"));
                        return;
                    }

                    HttpResponse<Page<Hotel>> httpResponse = response.body();
                    if (!httpResponse.isSuccess()) {
                        exploreResult.postValue(new HotelListResult(null, httpResponse.getMessage()));
                        return;
                    }

                    nearByHotelList.postValue(new HotelListResult(httpResponse.getData().getContent(), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<HttpResponse<Page<Hotel>>> call, @NonNull Throwable t) {
                exploreResult.postValue(new HotelListResult(null, "Something went wrong"));
            }
        });
    }

    public void fetchPopularHotelList(Integer page, Integer size) {
        hotelService.getPopularHotelList(page, size).enqueue(new Callback<HttpResponse<Page<Hotel>>>() {
            @Override
            public void onResponse(@NonNull Call<HttpResponse<Page<Hotel>>> call, @NonNull Response<HttpResponse<Page<Hotel>>> response) {
                try (ResponseBody errorBody = response.errorBody()) {
                    if (!response.isSuccessful() && errorBody != null) {
                        Gson gson = AppUtil.getGson();
                        Type type = new TypeToken<HttpResponse<String>>() {
                        }.getType();
                        HttpResponse<String> httpResponse = gson.fromJson(errorBody.charStream(), type);
                        exploreResult.postValue(new HotelListResult(null, httpResponse.getMessage()));
                        return;
                    }

                    if (response.body() == null) {
                        exploreResult.postValue(new HotelListResult(null, "Something went wrong"));
                        return;
                    }

                    HttpResponse<Page<Hotel>> httpResponse = response.body();
                    if (!httpResponse.isSuccess()) {
                        exploreResult.postValue(new HotelListResult(null, httpResponse.getMessage()));
                        return;
                    }

                    popularHotelList.postValue(new HotelListResult(httpResponse.getData().getContent(), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<HttpResponse<Page<Hotel>>> call, @NonNull Throwable t) {
                exploreResult.postValue(new HotelListResult(null, "Something went wrong"));
            }
        });
    }

    public void fetchExploreHotelList(Integer page, Integer size) {
        hotelService.getExploreHotelList(page, size).enqueue(new Callback<HttpResponse<Page<Hotel>>>() {
            @Override
            public void onResponse(@NonNull Call<HttpResponse<Page<Hotel>>> call, @NonNull Response<HttpResponse<Page<Hotel>>> response) {
                try (ResponseBody errorBody = response.errorBody()) {
                    if (!response.isSuccessful() && errorBody != null) {
                        Gson gson = AppUtil.getGson();
                        Type type = new TypeToken<HttpResponse<String>>() {
                        }.getType();
                        HttpResponse<String> httpResponse = gson.fromJson(errorBody.charStream(), type);
                        exploreResult.postValue(new HotelListResult(null, httpResponse.getMessage()));
                        return;
                    }

                    if (response.body() == null) {
                        exploreResult.postValue(new HotelListResult(null, "Something went wrong"));
                        return;
                    }

                    HttpResponse<Page<Hotel>> httpResponse = response.body();
                    if (!httpResponse.isSuccess()) {
                        exploreResult.postValue(new HotelListResult(null, httpResponse.getMessage()));
                        return;
                    }

                    exploreResult.postValue(new HotelListResult(httpResponse.getData().getContent(), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<HttpResponse<Page<Hotel>>> call, @NonNull Throwable t) {
                exploreResult.postValue(new HotelListResult(null, "Something went wrong"));
            }
        });
    }

    public void search(String keyword, @Nullable Integer page, @Nullable Integer size) {
        hotelService.search(keyword, page, size).enqueue(new Callback<HttpResponse<Page<Hotel>>>() {
            @Override
            public void onResponse(@NonNull Call<HttpResponse<Page<Hotel>>> call, @NonNull Response<HttpResponse<Page<Hotel>>> response) {
                try (ResponseBody errorBody = response.errorBody()) {
                    if (!response.isSuccessful() && errorBody != null) {
                        Gson gson = AppUtil.getGson();
                        Type type = new TypeToken<HttpResponse<String>>() {
                        }.getType();
                        HttpResponse<String> httpResponse = gson.fromJson(errorBody.charStream(), type);
                        searchResult.postValue(new HotelListResult(null, httpResponse.getMessage()));
                        return;
                    }

                    if (response.body() == null) {
                        searchResult.postValue(new HotelListResult(null, "Something went wrong"));
                        return;
                    }

                    HttpResponse<Page<Hotel>> httpResponse = response.body();
                    if (!httpResponse.isSuccess()) {
                        searchResult.postValue(new HotelListResult(null, httpResponse.getMessage()));
                        return;
                    }

                    searchResult.postValue(new HotelListResult(httpResponse.getData().getContent(), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<HttpResponse<Page<Hotel>>> call, @NonNull Throwable t) {
                searchResult.postValue(new HotelListResult(null, "Something went wrong"));
            }
        });
    }

    public void getHotelById(long id) {
        hotelService.getHotelById(id).enqueue(new Callback<HttpResponse<Hotel>>() {
            @Override
            public void onResponse(@NonNull Call<HttpResponse<Hotel>> call, @NonNull Response<HttpResponse<Hotel>> response) {
                try (ResponseBody errorBody = response.errorBody()) {
                    if (!response.isSuccessful() && errorBody != null) {
                        Gson gson = AppUtil.getGson();
                        Type type = new TypeToken<HttpResponse<String>>() {
                        }.getType();
                        HttpResponse<String> httpResponse = gson.fromJson(errorBody.charStream(), type);
                        hotel.postValue(new HotelResult(null, httpResponse.getMessage()));
                        return;
                    }

                    if (response.body() == null) {
                        hotel.postValue(new HotelResult(null, "Something went wrong"));
                        return;
                    }

                    HttpResponse<Hotel> httpResponse = response.body();
                    if (!httpResponse.isSuccess()) {
                        hotel.postValue(new HotelResult(null, httpResponse.getMessage()));
                        return;
                    }

                    hotel.postValue(new HotelResult(httpResponse.getData(), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<HttpResponse<Hotel>> call, @NonNull Throwable t) {
                hotel.postValue(new HotelResult(null, "Something went wrong"));
            }
        });
    }
}
