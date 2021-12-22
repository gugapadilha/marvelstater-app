package daniel.lop.io.marvelappstarter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import daniel.lop.io.marvelappstarter.data.remote.ServiceApi
import daniel.lop.io.marvelappstarter.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton //permite que a gente atualize a busca dos dados, seja pela api, ou banco de dados (singleton mantem uma instancia unica do okhttp durante toda a execução
    @Provides //ele provê essa instancia que a gente quer
    fun provideOkHttpClient() : OkHttpClient{
        return  OkHttpClient()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit{ //essa funcao tem uma depencia do okhttp cliente, pra ela funcionar, tem que passar primeiro pela okhttpclient
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideServiceApi(retrofit: Retrofit): ServiceApi{
        return retrofit.create(ServiceApi::class.java)
    }

}