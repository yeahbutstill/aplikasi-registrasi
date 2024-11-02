# Aplikasi Registrasi #

Persiapan:

Tambahkan entri berikut di `/etc/hosts`

```
127.0.0.1 auth-server
127.0.0.1 resource-server
127.0.0.1 client-app
```

Cara menjalankan:

1. Jalankan aplikasi `auth-server` yang ada di [repo BFF](https://gitlab.com/endymuhardin/belajar-spring-oauth-bff/-/tree/main/authserver)

2. Browse ke `http://client-app:10002/home`

3. Login dengan username `user001` dan password `teststaff`

4. Harusnya halaman `home` terbuka dan menampilkan informasi nama, username, dan authorities.