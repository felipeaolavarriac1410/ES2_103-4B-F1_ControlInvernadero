# Mi Primera Aplicación

Un proyecto inicial de Android creado para aprender los conceptos básicos del desarrollo móvil en **TI3V42 - Aplicaciones Móviles para IoT** (INACAP Puente Alto).

---

## 📱 Contenido de la Sesión: Construcción Incremental del Login y Navegación

En esta sesión se desarrolló una pantalla de Login completa y la navegación hacia una segunda pantalla mediante los siguientes 10 incrementos:

1. **Incremento 1 (`ImageView` - Logo INACAP):**
   - Agregado de `imgLogo` en `activity_main.xml` utilizando el recurso `@drawable/logo_inacap`.
   - Dimensiones de 96x96dp, centrado horizontalmente y margen superior de 32dp.

2. **Incremento 2 (`TextView` - Título):**
   - Agregado de `txtTitulo` con el texto `"Iniciar sesión"`, tamaño `24sp`, negrita y posicionado debajo del logo.

3. **Incremento 3 (`EditText` - Campo Usuario):**
   - Agregado de `edtUsuario` con `hint="Usuario"`, ancho variable (`0dp` / match constraints) y márgenes laterales de 24dp.

4. **Incremento 4 (`EditText` - Campo Contraseña):**
   - Agregado de `edtPassword` con `hint="Contraseña"` y `android:inputType="textPassword"` para enmascarar la entrada del usuario.

5. **Incremento 5 (`CheckBox` - Recordar sesión):**
   - Agregado de `chkRecordarme` con el texto `"Recordarme"` ubicado bajo el campo de contraseña.

6. **Incremento 6 (`Button` - Botón Ingresar):**
   - Agregado de `btnIngresar` con el texto `"Ingresar"`, ancho completo alineado a los campos de entrada y margen superior de 20dp.

7. **Incremento 7 (Lógica del Botón en Kotlin):**
   - Vinculación del botón mediante `android:onClick="onIngresarClick"`.
   - Implementación del método `onIngresarClick(view: View)` en `MainActivity.kt` con captura de vistas mediante `findViewById`.
   - Validación de campos requeridos (si usuario o contraseña están vacíos, muestra un `Toast`).

8. **Incremento 8 (Segunda Pantalla - `BienvenidaActivity`):**
   - Creación de `BienvenidaActivity.kt` y su layout `activity_bienvenida.xml`.
   - Declaración de la nueva Activity en `AndroidManifest.xml`.
   - Agregado de `txtBienvenida` centrado en la pantalla con `24sp` y negrita.

9. **Incremento 9 (Navegación con `Intent`):**
   - Conexión del flujo de navegación desde `MainActivity.kt` hacia `BienvenidaActivity` mediante `Intent(this, BienvenidaActivity::class.java)` y `startActivity(intent)` cuando la validación es exitosa.

10. **Incremento 10 (Paso de Parámetros entre Pantallas):**
    - Envío del nombre de usuario a través del Intent con `intent.putExtra("usuario", usuario)`.
    - Lectura del parámetro en `BienvenidaActivity.kt` con `intent.getStringExtra("usuario")` y visualización dinámica en el TextView: `"Bienvenido, <usuario>"`.

---

## 🛠️ Tecnologías y Herramientas

- **Lenguaje:** Kotlin
- **Diseño de Interfaz:** Android XML (ConstraintLayout)
- **SDK Mínimo:** Android API 24+
- **Control de Versiones:** Git & GitHub CLI (`gh`)

