## Microbenchmark svar 
Starting microbench...

Valgte parametre - Boids=2000, warmup=50, measured steps=200, neighbor radius=60

- ---NaiveSpatialIndex---
    - NaiveSpatialIndex: total = 0.087 ms, avg per step = 0.0004 ms

- ---KDTreeSpatialIndex---
    - KDTreeSpatialIndex: total = 0.033 ms, avg per step = 0.0002 ms

- ---QuadTreeSpatialIndex---
    - QuadTreeSpatialIndex: total = 0.047 ms, avg per step = 0.0002 ms

- ---SpatialHashIndex---
    - SpatialHashIndex: total = 0.018 ms, avg per step = 0.0001 ms



## Microbenchmark argumentation
### Hvorfor disse tal?
**Boids**

Idéen med at vælge 2000 boids er, at man med det tal faktisk kan vise en forskel på de asymptotiske forhold mellem de forskellige datastrukturer.
For få boids, f.eks. 50-200, ville simpelthen ikke gøre stor nok forskel på selve benchmarks'ne, da forskellen ligger i scalingen af de forskellige datastruktur - derfor har man brug for et stort nok datasæt.
Ved 2000 boids kan man se en klar forskel på datastrukturerne, specifikt med fokus på at Naive Spatial Index er den langsommeste af de fire muligheder.

**Warmup**

Formålet med 50 warmup iterationer er, at computere vil optimere kodeiterationer løbende. De første par iterationer vil være langsommere end hvad der er den egentlige køretid, grundet ting som cpu/ram caching et al.
Ved at have 50 warmups når computeren at normalisere, og giver et mere pålideligt resultat, uden at vi skal vente for lang tid på resultaterne (nok ikke det største problem når vi snakker tid under 1 ms).


**Neighbor radius**

Valget her er grundet at det er en realistisk radius for boids at have - Det ville gøre iterationerne ekstremt langsomme hvis det havde en meget stor radius, da den ville se ekstremt mange naboer som den skulle regne på. På samme tid vil man heller ikke have det for småt, for ellers bliver der meget få beregninger (relativt), hvilket ville gøre forskellen mellem de forskellige strukturer mindre, og derved mindre pålidelige.

