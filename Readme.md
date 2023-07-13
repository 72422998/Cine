# Cine

---

## Comandos git

Versionamiento del proyecto
```cmd
git tag <nombre_version>

```
Subir versiones del local al remoto
```cmd
git push origin --tags

```
Eliminar tag de manera local
```
git tag -d <nombre_tag>
```
Eliminar tag de manera remota
```
git push origin --delete <nombre_tag>
```
Ver tags existentes
```
git tag
```
Ver información adicional sobre los tags
```
git show <nombre_tag>
```
Ver lista de tags remotos
```
git ls-remote --tags origin
```
Descartar cambios
```
git checkout -- .
```
Traer una rama remota y cambiarse a ello
```
git checkout -b <nombre-de-la-rama> origin/<nombre-de-la-rama>
```