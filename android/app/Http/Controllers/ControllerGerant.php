<?php

namespace App\Http\Controllers;

use App\Models\Commande;
use App\Models\Fournisseur;
use App\Models\Produit;
use Illuminate\Http\Request;

class ControllerGerant extends Controller
{

    public function addProduit(Request $request){


        $this->validate($request, [
            'nom' => 'required',
            'prixU' => 'required',


        ]);

        // $prixP = $request->prixU *1.445;
        $post = new Produit();
        $post->nom =$request->nom;
        $post->prixU =$request->prixU;
        $post->prixP =$request->prixU *1.445;
        $post->save();
        return response()->json($post->nom);
    }

        public function getAllProduit() {

            //where('user_id',auth()->user()->id)->get()
            $data = Produit::all();
            if(is_null($data)){
                return response()->json(['message'=>'pas encore fait de depense']);
            }
            return response()->json($data, 200);
        }

    public function getProduitById($id) {
        $produit = Produit::find($id);
        if(is_null($produit)) {
            return response()->json(['message' => 'produit non trouvé'], 404);
        }
        return response()->json($produit, 200);
    }

    public function updateProduit(Request $request, $id) {

        $produit = Produit::find($id);
        if(is_null($produit)) {
            return response()->json(['message' => 'produit n\'existe pas'], 404);
        }
        $produit->update($request->all());
        return response($produit, 200);
    }

    public function deleteProduit($id) {
        $produit = Produit::find($id);
        if(is_null($produit)) {
            return response()->json(['message' => 'produit n\'existe pas'], 404);
        }
        $produit->delete();
        return response()->json("Bien fait", 204);
    }
//ajout commande

    public function addCommande(Request $request){
        $this->validate($request,[
            'qte'=> 'required',
            'date'=> 'required',
            'produit_id'=> 'required',
            'fournisseur_id'=> 'required'
        ]);

        $commande = Commande::create([
            'qte' => $request->qte,
            'date' => $request->date,
            'produit_id' => $request->produit_id,
            'fournisseur_id' => $request->fournisseur_id,

        ]);
        return response()->json([
            'commande' => $commande
        ],200);
    }


    public function getAllcommande() {
        $data = Commande::all();
        if(is_null($data)){
            return response()->json(['message'=>'pas encore fait de depense']);
        }
        return response()->json($data, 200);
    }
    public function getCommandeById($id) {
        $commande = Commande::find($id);
        if(is_null($commande)) {
            return response()->json(['message' => 'produit non trouvé'], 404);
        }
        return response()->json($commande, 200);
    }

    public function updateCommande(Request $request, $id) {

        $commande = Commande::find($id);
        if(is_null($commande)) {
            return response()->json(['message' => 'produit n\'existe pas'], 404);
        }
        $commande->update($request->all());
        return response($commande, 200);
    }

    public function deleteCommande($id) {
        $commande = Commande::find($id);
        if(is_null($commande)) {
            return response()->json(['message' => 'produit n\'existe pas'], 404);
        }
        $commande->delete();
        return response()->json(null, 204);
    }
    // ajout founisseur

    public function addFournisseur(Request $request)
    {

        $this->validate($request, [
            'nom' => 'required',
            'prenom' => 'required',
            'adresse' => 'required',
            'telephone' => 'required',
            'email' => 'required',

        ]);

        $user = Fournisseur::create([
            'nom' => $request->nom,
            'prenom' => $request->prenom,
            'adresse' => $request->adresse,
            'telephone' => $request->telephone,
            'email' => $request->email,


        ]);
        return response()->json([
            'user'=>$user
        ], 200);

    }


    public function getAllFournisseur() {
        $this->middleware(['role:pharmacien']);
        $data = Fournisseur::all();
        if(is_null($data)){
            return response()->json(['message'=>'pas encore fait de depense']);
        }
        return response()->json($data, 200);
    }
    public function getFournisseurById($id) {
        $fournisseur = Fournisseur::find($id);
        if(is_null($fournisseur)) {
            return response()->json(['message' => 'produit non trouvé'], 404);
        }
        return response()->json($fournisseur, 200);
    }

    public function updateFournisseur(Request $request, $id) {

        $fournisseur = Fournisseur::find($id);
        if(is_null($fournisseur)) {
            return response()->json(['message' => 'produit n\'existe pas'], 404);
        }
        $fournisseur->update($request->all());
        return response($fournisseur, 200);
    }

    public function deleteFournisseur($id) {
        $fournisseur = Fournisseur::find($id);
        if(is_null($fournisseur)) {
            return response()->json(['message' => 'produit n\'existe pas'], 404);
        }
        $fournisseur->delete();
        return response()->json(null, 204);
    }
//fin ajout de fournisseur



}
