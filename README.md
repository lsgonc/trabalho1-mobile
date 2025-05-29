# App de Bem Estar - Atividade Avaliativa 1
Aplicativo móvel desenvolvido para projeto 1 da matéria de Desenvolvimento Móvel - Prof. André Takeshi Endo (DC UFSCar)

- Lucas Sciarra Gonçalves - 811948
- Giuseppe Chaves Magnago - 811164

### O que foi utilizado:
- Android Studio Meerkat Feature Drop | 2024.3.2 Patch 1 (Build #AI-243.26053.27.2432.13536105)
- Runtime version: 21.0.6+-13368085-b895.109 amd64
- VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
- Kotlin plugin: K2 mode
- JetPack Compose

### Requisitos:
  - R1: O aplicativo deve ter uma identidade visual e um layout bem definidos, usando os conceitos de layout vistos no curso. Este requisito é um pouco subjetivo, mas a ideia é que o aplicativo tenha aparência de um aplicativo real, algo que você mostraria a um amigo (ou um investidor, quem sabe?). Uma paleta de cores bem pensada, seguindo o Material Design, por exemplo, é essencial!
      - Nós não focamos **MUITO** no quesito visual do aplicativo, apenas fizemos com que fosse agradável aos olhos, mas nada muito elaborado. Fizemos algo que achamos bonito mas priorizamos a funcionalidade.
  - R2: O aplicativo deve ter mais do que uma “tela” (activity/intent/fragment/página ou conceito similar). Neste caso, é necessário um mínimo de 2 telas por participante; por exemplo, uma equipe com 2 integrantes deve construir um aplicativo com pelo menos 4 telas.
      - Construímos um aplicativo com 6 telas. Não fizemos a divisão de 3 telas para cada um, ao invés disso, dividimos a implementação de funcionalidades, o número de telas não era algo que achamos muito significativo para isso. De todo modo, coincidetemente, a quantidade de telas para cada um ficou parecida.
  - R3: O aplicativo deve acessar a rede. O acesso à rede pode ser com um back-end falso, mas deve existir e estar implementado.
      - Nosso app faz acesso a internet, fazendo requisições à uma API. Também possui um back-end para manipulação e conversão dos dados recebidos pela API. Para isso, utilizamos o Retrofit, assim como o professor disponibiliza nos exemplos.
  - R4: O aplicativo deve armazenar alguma coisa localmente, preferencialmente via SQLite/Android Room.
      - Nosso armazena algumas respostas da API no banco local. Utilizamos o Android Room para isso, assim como o professor utilizou nos códigos de exemplo.
  - R5: O aplicativo deve possuir suporte básico de internacionalização, oferecendo tradução dos textos para pelo menos dois idiomas (ex: português e inglês). Não pode haver strings “hardcoded”!!
      - Nosso aplicativo possui suporte a internacionalização, oferecendo os idiomas português e inglês. Ele é 100% internacionalizado, a única coisa que não pudemos internacionalizar foi as respostas da API, ela até oferece suporte para resposta em português mas, com isso, os resultados ficaram muito mais limitados (pouca quantidade de respostas diferentes).
  - R6: O projeto deve seguir as diretrizes e boas práticas para desenvolvimento móvel discutidas em sala de aula, tais como ViewModels, classes Repository, e testes automatizados para Web APIs e/ou persistência local.
      - Foi utilizado várias diretrizes e boas práticas, como ViewModels, Repository, modularização, DAOs, services, compose etc.
  - Como uma extra, nosso projeto faz acesso ao recurso de câmera do telefone, solicitando a permissão de acesso. Para isso, utilizamos a biblioteca [CameraX](https://developer.android.com/jetpack/androidx/releases/camera?hl=pt_br).

### Como executar:
Para executar o projeto, dê um git clone do projeto e abra-o no Android Studio na versão mais recente. 
Primeiramente, vá no arquivo build.gradle.kts (Project) e adicione os seguintes plugins:
```
plugins {
    ...
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}
```
Em seguida, no arquivo gradle.kts (module: app) e adicione os seguintes plugins e dependências:
```
plugins {
    ...
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

dependencies {
    ...
    implementation("androidx.camera:camera-core:1.3.0")
    implementation ("androidx.camera:camera-camera2:1.3.0")
    implementation ("androidx.camera:camera-lifecycle:1.3.0")
    implementation ("androidx.camera:camera-view:1.3.0")

    implementation("androidx.navigation:navigation-compose:2.7.5") // ou versão mais recente
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1") // Para suporte a coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    ksp("androidx.room:room-compiler:2.6.1")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.room:room-runtime:2.7.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}
```
Feito isso, faça a sincronização dos arquivos, ele deve buildar tudo certinho. Com isso, selecione o dispositivo em que deseja rodar o app e clique "Run".

## Referências
1- [04-2-One2NineAppWithRoom_kt2 - Prof. André Takeshi Endo](https://github.com/andreendo/mobapps-course/tree/master/04-2-One2NineAppWithRoom_kt2)
2- [Android Developers Jetpack](https://developer.android.com/jetpack?hl=pt-br)
