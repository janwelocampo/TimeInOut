package dstudio.com.healthway.retrofit;

import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.model.Profile;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by janwelcris on 10/19/2017.
 */

public interface APIInterface {

    @GET("/janwelocampo/AndroidTutorial/master/mvpdemo.json")
    Observable<Profile> doGetProfileData();
}
