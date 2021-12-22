package daniel.lop.io.marvelappstarter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

 //hilt vai gerar o codigo de nivel de aplicacao e vai fornecer todas as dependencias
 //de qualquer entidade do android framework
 @HiltAndroidApp
class MarvelApplication: Application() {
}