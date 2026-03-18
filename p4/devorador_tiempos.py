import time
import json
from typing import Any, Dict
from coloreado_grafo import realizar_voraz

def cargar_grafo_desde_json(archivo: str) -> Dict[str, Any]:
    with open(archivo, 'r') as f:
        grafo = json.load(f)
    return grafo

if __name__ == "__main__":

    n= 8
    while n <= 65537: 
        archivo_grafo = f"sols/g{n}.json"  
        try:
            mapa = cargar_grafo_desde_json(archivo_grafo)
            
            inicio = time.time()  
            solucion = realizar_voraz(mapa["grafo"])  
            fin = time.time()  
            
            tiempo = (fin - inicio) * 1000
            print(f"Tiempo para grafo de tamaño {n}: {tiempo:.2f} milisegundos")
        
        except FileNotFoundError:
            print(f"No se encontró el archivo para el grafo de tamaño {n}.")
            continue
        n*=2

