# Text to Image demo API

El proyecto es una API diseñada para convertir texto en imágenes utilizando la tecnología de AI proporcionada por [Stability AI](https://stability.ai). A través de la integración con [Spring AI](https://spring.io/projects/spring-ai), este proyecto es capaz de interpretar descripciones escritas (prompts) y materializarlas en imágenes visuales detalladas y realistas.

La API está diseñada para ser sencilla de usar, contando con un único punto de acceso `/generate-image` que espera recibir una solicitud en formato JSON. Los usuarios simplemente tienen que enviar su prompt deseado junto con especificaciones de tamaño y cantidad de ejemplos, y la API se encarga del resto.

Ejemplo de solicitud al endpoint `/generate-image`:

```json
{
    "prompt": "create a 4x6 postcard of an astronaut riding a horse: high quality, realistic",
    "width": 682,
    "height": 1024,
    "samples": 4
}
```

Cada atributo de la solicitud tiene un propósito específico:

- **prompt:** La descripción textual de la imagen que se quiere generar. Puede ser tan detallada o tan abierta como el usuario desee.
- **width:** La anchura deseada de la imagen resultante en píxeles.
- **height:** La altura deseada de la imagen resultante en píxeles.
- **samples:** El número de variaciones únicas de la imagen que se generarán basadas en el prompt proporcionado.

Esta API está pensada para aquellos que buscan experimentar con la última tecnología de IA en el espacio de generación de imágenes y quieren hacerlo con la facilidad de uso y la robustez que proporciona el marco de trabajo de Spring. No se recomienda su uso en producción.
