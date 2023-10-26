# API

## Сущности

1. Interest (интерес)
2. Profile (анкета пользователя)

### Описание сущности Interest

1. name (String)

### Oписание сущности Profile

1. Owner (userId)
2. Name  (String)
3. Gender (male|female|null)
4. Description (String)
3. Contact (String)
4. Interests (List<Interest>)

## Функции (эндпониты)

1. Interest CRUDS
   1. create (admin only)
   2. read
   3. update (admin only)
   4. delete (admin only)
   5. search - поиск по фильтрам

2. Profile CRUDS
   1. create
   2. read
   3. update
   4. delete
   5. search - поиск по фильтрам

