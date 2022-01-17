<?php

namespace App\Http\Controllers;

use App\Models\Commande;
use App\Models\Depot;
use App\Models\FamilleProduit;
use App\Models\Produit;
use App\Models\Vente;
use Facade\FlareClient\Http\Response;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class VenteController extends Controller
{
    public function addForme(Request $request){
        $this->validate($request,[
            'forme'=> 'required'
        ]);

        $post = FamilleProduit::create([
            'forme' => $request->forme,
        ]);
        return response()->json([
        'famille'=>$post,
        ],200);
    }

    public function search($name){
      return Produit::where("nom","like","%".$name."%")->get();

    }

    public function vente(Request $request){
        $this->validate($request,[
            'qte' => 'required',
            'verse' => 'required',
            'produit' => 'required',

        ]);
        $put = DB::table('produits')
            ->select('produits.prixU')
            ->where('produits.nom',$request->produit)
            ->pluck('prixU');

        $vente = new Vente() ;
        $vente->qte = $request->qte;
        $vente->verse = $request->verse;
        $vente->produit = $request->produit;
        $vente->rendu = $request->verse - $put[0]*$request->qte;
        $vente->save();
        return response()->json($vente->rendu);
    }

    public function facture(Request $request){

    }
}
