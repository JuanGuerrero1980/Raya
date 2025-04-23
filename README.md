# 💱 Raya Currency Converter

Una app multiplataforma construida con **Kotlin Multiplatform + Compose Multiplatform** que permite visualizar balances en diferentes monedas (ARS, USD, BTC, ETH) y realizar conversiones en tiempo real usando datos obtenidos desde la API pública de **CoinGecko**.

---

## 🤔 ¿Por qué lo resolvimos así?

### 🔍 Racional técnico

Elegimos **Kotlin Multiplatform + Compose** para cumplir con el requerimiento de compatibilidad Android/iOS, aprovechando:

- **UI declarativa unificada** (Compose Multiplatform) → una sola base de código para Android y iOS.
- **Ktor** para la comunicación HTTP con CoinGecko, compatible con multiplataforma.
- **Room** como almacenamiento local, facilitando el guardado de balances y posibles futuras funcionalidades como historial o sincronización offline.
- **Arquitectura basada en ViewModels** (usando `StateFlow`) para manejar el estado de forma clara y reactiva.
- **Inyección de dependencias** con Koin, facilitando testeo y modularidad.
- **Cacheo automático de tasas de conversión** para evitar múltiples llamadas innecesarias a la API.

Este stack permite escalar fácilmente el proyecto (por ejemplo, agregar login, estadísticas, sincronización en la nube, etc.) sin perder mantenibilidad.

---

## ✅ Funcionalidades

- Mostrar balances en ARS, USD, BTC y ETH.
- Convertir entre cualquiera de estas monedas.
- Obtener precios actualizados desde CoinGecko.
- Cachear temporalmente las tasas para optimizar llamadas.
- Utiliza **Room** como base de datos local para guardar información relevante.

---

## 🚀 Cómo correr el proyecto

### 🧩 Requisitos

- JDK 17+
- Kotlin 1.9+
- Android Studio **Hedgehog** o superior con soporte KMP
- (Opcional) Xcode si querés compilar para iOS más adelante

---

1. Cloná el repositorio:

   ```bash
   https://github.com/JuanGuerrero1980/Raya.git

2. Abrí el proyecto con Android Studio.

   - Si es la primera vez que abrís un proyecto KMP, Android Studio te va a pedir que instales algunos plugins. Aceptá y reiniciá el IDE.

   - Si no tenés el plugin de Compose Multiplatform, instalalo desde `Preferences > Plugins > Marketplace` buscando "Compose Multiplatform".

   - Asegurate de tener configurado el SDK de Android y el emulador.

3. Hacé click en Run ▶️ para correr la app en un emulador o dispositivo Android.

4. Para correr la app en iOS, hacé click en Run `iosApp` y ejecutá el proyecto desde ahí. Asegurate de tener instalado Xcode, un simulador o dispositivo iOS conectado.