package com.example.khapharma.service

import com.example.khapharma.Gerant.BodyProduit
import com.example.khapharma.models.AllUser
import com.example.khapharma.models.CompteBody
import com.example.khapharma.models.AllStock
import com.example.khapharma.models.AllStockItem
import com.example.khapharma.models.ajout
import com.example.khapharma.models.*
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    //pour les users
    @POST("api/login")
    suspend fun login(@Body() body: LoginBody): Response<LoginUser>

    @POST("api/forgot_password")
    suspend fun forgot_password(@Body() body:LoginBody):Response<User>

    @POST("api/add_user")
    suspend fun add_user(@Body() body: CompteBody):Response<String>

    @GET("api/test")
    suspend fun test(): Response<String>

    //pour stock
    @GET("api/allstock")
    suspend fun allStock():Response<AllStock>

    @DELETE("api/delete_stock/{id}")
    suspend fun  deleStock(@Path("id") id:Int):Response<AllStockItem>

    @POST("api/add_stock")
    suspend fun  addStock(@Body() body: ajout):Response<AllStock>

    // pour vente
    @POST("api/new_vente")
    suspend fun NewVente(@Body() body: VenteBody):Response<Int>

    //pour les ulilisateur

    @GET("api/all_user")
    suspend fun allUser():Response<AllUser>

    @DELETE("api/delete_user/{id}")
    suspend fun deleteUser(@Path("id") id: Int):Response<String>

    //pour produit

    @POST("api/add_produit")
    suspend fun newProduit(@Body() body:ProduitBody):Response<String>

    @GET("api/all_produit")
    suspend fun allProduit():Response<BodyProduit>

}