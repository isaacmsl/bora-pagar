/**
 * Receba uma função e aplique o debounce nela.
 * Isto é, se a função for chamada várias vezes em um intervalo de tempo, ela só será executada uma vez.
 * @param f função a ser chamada
 * @param debounceTime Intervalo de tempo em milisegundos
 */
export function debounce(f : Function, debounceTime = 500) {
  let timer: number;

  return () => {
    clearTimeout(timer);
    timer = setTimeout(f, debounceTime);
  }
}