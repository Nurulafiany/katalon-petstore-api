# katalon-petstore-api

Project automation API (WEBSERVICE) untuk **Katalon Studio Enterprise 10.x** yang menguji endpoint Petstore.

## Deskripsi Project

Project ini berisi rangkaian test case API untuk memverifikasi fungsi CRUD utama pada Petstore API.
Setiap endpoint diuji dengan skenario **positif** dan **negatif**, ditambah satu skenario **End-to-End (E2E)**
yang menjalankan siklus hidup lengkap sebuah pet (create → read → update → read → delete → verify).

Seluruh data uji bersifat dinamis: base URL, category, status, dan API key diambil dari **Global Variable**,
sedangkan `petId` dan `petName` dibuat saat runtime memakai `System.currentTimeMillis()` sehingga tidak ada
hardcoded value di dalam script.

## Target API

| Item | Value |
|---|---|
| Base URL | https://petstore.swagger.io/v2 |
| Swagger UI | https://petstore.swagger.io/ |
| Autentikasi | Header `api_key` (nilai demo: `special-key`) |

## Endpoint & Coverage

| No | Method | Endpoint | Test Case | Tipe | DDT |
|---|---|---|---|---|---|
| 1 | POST | `/pet` | TC01_POST_AddPet_Positive | positive | GlobalVariable (category, status) + runtime petId/petName |
| 2 | POST | `/pet` | TC02_POST_AddPet_Negative | negative | Empty body |
| 3 | PUT | `/pet` | TC03_PUT_UpdatePet_Positive | positive | GlobalVariable (updatedStatus) + runtime petId/petName |
| 4 | PUT | `/pet` | TC04_PUT_UpdatePet_Negative | negative | Malformed JSON body |
| 5 | GET | `/pet/{petId}` | TC05_GET_GetPet_Positive | positive | runtime petId (hasil create) |
| 6 | GET | `/pet/{petId}` | TC06_GET_GetPet_Negative | negative | GlobalVariable (invalidPetId) |
| 7 | DELETE | `/pet/{petId}` | TC07_DELETE_DeletePet_Positive | positive | runtime petId + GlobalVariable (apiKey) |
| 8 | DELETE | `/pet/{petId}` | TC08_DELETE_DeletePet_Negative | negative | GlobalVariable (invalidPetId) |
| 9 | E2E | `/pet` + `/pet/{petId}` | TC09_E2E_Pet_Lifecycle | e2e | GlobalVariable + runtime petId/petName |

Total: **9 test case** (4 positif, 4 negatif, 1 E2E).

## Global Variables

Didefinisikan di `Profiles/default.glbl`.

| Name | Value | Keterangan |
|---|---|---|
| baseUrl | `https://petstore.swagger.io/v2` | Base URL API (nilai demo publik) |
| apiKey | `special-key` | API key demo publik untuk endpoint DELETE |
| categoryId | `1` | ID category untuk payload pet |
| categoryName | `dog` | Nama category untuk payload pet |
| petStatus | `available` | Status awal pet |
| updatedStatus | `sold` | Status setelah update |
| invalidPetId | `999999999` | ID pet yang dijamin tidak ada (skenario negatif) |

> **Catatan:** profil dan data pada project ini hanya berisi **nilai demo**. Jangan pernah meng-commit
> password, token, API key, atau secret asli ke repository. Gunakan `Profiles/default.glbl.example`
> sebagai template dan isi nilai rahasia secara lokal.

## Struktur Project

```
Object Repository/Petstore/
  AddPet.rs, AddPetInvalid.rs, UpdatePet.rs, UpdatePetInvalid.rs, GetPet.rs, DeletePet.rs
Test Cases/Petstore/
  CreatePet/TC01_POST_AddPet_Positive.tc
  CreatePet/TC02_POST_AddPet_Negative.tc
  UpdatePet/TC03_PUT_UpdatePet_Positive.tc
  UpdatePet/TC04_PUT_UpdatePet_Negative.tc
  GetPet/TC05_GET_GetPet_Positive.tc
  GetPet/TC06_GET_GetPet_Negative.tc
  DeletePet/TC07_DELETE_DeletePet_Positive.tc
  DeletePet/TC08_DELETE_DeletePet_Negative.tc
  E2E/TC09_E2E_Pet_Lifecycle.tc
Scripts/Petstore/  (script .groovy untuk setiap test case)
Test Suites/TS_Petstore_API_Regression.ts
Profiles/default.glbl
Libs/internal/GlobalVariable.groovy
```

## Cara Menjalankan

### 1. Melalui Katalon Studio IDE

1. Buka Katalon Studio, pilih **File > Open Project** dan arahkan ke folder project ini.
2. Buka **Test Suites > TS_Petstore_API_Regression**.
3. Klik tombol **Run** dan pilih browser/device profile (API test tidak memerlukan browser).
4. Tunggu hingga eksekusi selesai dan lihat hasil pada tab **Log Viewer** / **Report**.


## Lokasi Report

Hasil eksekusi tersimpan di folder `Reports/` dengan format:

- `Reports/<timestamp>/report.html` — laporan HTML.
- `Reports/<timestamp>/report.csv` / `.xml` / `.junit` — laporan machine-readable.

Folder `Reports/` **tidak** di-commit (lihat `.gitignore`).
