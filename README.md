1. Otvoriti projekt u IntelliJ IDEA i pokrenuti main funkciju BackendAkademijaApplication klase.

2. Otvoriti u pregledniku: http://localhost:8080/swagger-ui/index.html
3. Pod Auth POST /api/auth/login prijaviti se kao korisnik ili admin:

Korisnik: 
{
  "username": "oliviaw",
  "password": "oliviawpass"
}

Admin: 
{
  "username": "emilys",
  "password": "emilyspass"
}

4. Kopirati accessToken iz odgovora, kliknuti na gumb Authorize na vrhu stranice i upisati accessToken u bearerAuth.
5. Admin može pristupiti GET /api/products/{id}, dok korisnik ne može. Ostale krajnje točke zahtjevaju da je bilo koji korisnik ulogiran.
6. Također je moguće testiranje svake krajnje točke putem Postmana (potrebno postaviti Bearer Token). 
   
