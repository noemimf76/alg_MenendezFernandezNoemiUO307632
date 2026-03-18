import json
from typing import Any, Dict

from auxiliar import dibujar_mapa_coloreado, generar_mapa_grafo

def realizar_voraz(grafo: Dict[str, Any]):
    solucion: Dict[str, str] = {}
    colores = ["red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta", "lime"]


    for nodo in grafo:
        coloresUsados = set()
        for nodoAdy in grafo.get(nodo):
            nodoAdyacente = str(nodoAdy)
            colorAdy = solucion.get(nodoAdyacente)
            if(colorAdy != None):
                coloresUsados.add(colorAdy)
        colorIndice = 0
        while(colores[colorIndice] in coloresUsados):
            colorIndice += 1
        solucion[nodo] = colores[colorIndice]
    return solucion



if __name__ == "__main__":
    n = 4
    mapa = generar_mapa_grafo(n)
    solucion = realizar_voraz(mapa["grafo"])

    if solucion:
        print("Solución encontrada:", solucion)
        #dibujar_mapa_coloreado(mapa, solucion)
        with open('sols/solucion.json', 'w') as f:
            json.dump(solucion, f)
            f.close()
    else:
        print("No se encontró solución.")
