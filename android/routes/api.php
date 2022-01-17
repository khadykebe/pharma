<?php

use App\Http\Controllers\ProduitController;
use App\Http\Controllers\UserController;
use App\Models\User;
use GuzzleHttp\Middleware;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\Http\Controllers\ControllerPharmacien;
use App\Http\Controllers\ControllerGerant;
use App\Http\Controllers\VenteController;
use App\Http\Controllers\NewPasswordController;


use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

/*Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
*/

//route for all users

Route::post('login',[UserController::class,'login']);
Route::post('forgot_password', [NewPasswordController::class, 'forgotPassword']);
Route::post('update_password', [NewPasswordController::class, 'updatePassword']);



//route for  pharmacien

// Route::post('add_user',[ControllerPharmacien::class,'addUser']);


Route::middleware(['auth:api', 'role:pharmacien'])->group(function(){



});
Route::post('add_user',[ControllerPharmacien::class,'addUser']);
Route::post('add_stock',[ControllerPharmacien::class,'addStock']);
Route::get('allstock',[ControllerPharmacien::class,'getAllStock']);
Route::delete('delete_stock/{id}',[ControllerPharmacien::class,'deleteStock']);



//route for gerant and pharmacien
Route::get('all_user',[ControllerPharmacien::class,'getAllUser']);


Route::middleware(['auth:api','role:gerant'])->group(function(){
    Route::post('add_commande',[ControllerGerant::class,'addCommande']);
    Route::get('one_user/{id}',[ControllerPharmacien::class,'getUserById']);
    Route::put('update_user/{id}',[ControllerPharmacien::class,'updateUser']);
    Route::delete('delete_user/{id}',[ControllerPharmacien::class,'deleteUser']);



});

//route pour vendeur
Route::get('all_produit',[ControllerGerant::class,'getAllProduit']);

Route::middleware(['auth:api','role:vendeur'])->group(function(){
    Route::get('one_produit/{id}',[ControllerGerant::class,'getProduitById']);
    Route::put('update_produit/{id}',[ControllerGerant::class,'updateProduit']);
    Route::delete('delete_produit/{id}',[ControllerGerant::class,'deleteProduit']);

});

Route::post('add_produit',[ControllerGerant::class,'addProduit']);
Route::post('new_vente',[VenteController::class,'vente']);


Route::get('search/{name}',[VenteController::class,'search']);


// Route::post('add_produit',[ProduitController::class,'addProduit']);

// Route::get('all_produit',[ProduitController::class,'getAllProduit']);

// Route::get('one_produit/{id}',[ProduitController::class,'getProduitById']);

// Route::put('update_produit/{id}',[ProduitController::class,'updateProduit']);

// Route::delete('delete_produit/{id}',[ProduitController::class,'deleteProduit']);


Route::post('add_famille',[VenteController::class,'addFamille']);

// Route::post('add_depot',[VenteController::class,'addDepot']);

// Route::post('add_commande',[VenteController::class,'addCommande']);

// Route::post('add_forme',[ProduitController::class,'addForme']);

// Route::get('user',function(){
//     return User::All();
// });

// Route::get('user',function(){
//     return Auth::user();
// })->middleware("auth:api") ;




Route::get("test", function(){
    return response()->json('Message de teste');
});
