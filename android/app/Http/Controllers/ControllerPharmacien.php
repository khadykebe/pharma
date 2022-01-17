<?php

namespace App\Http\Controllers;

use App\Models\Depot;
use App\Models\Fournisseur;
use App\Models\Stock;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class ControllerPharmacien extends Controller
{

    // personnelle  pharmacie
    public function addUser(Request $request)
    {

            $this->validate($request, [
                'nom' => 'required',
                'prenom' => 'required',
                'adresse' => 'required',
                'telephone' => 'required',
                'email' => 'required|email',
                'password' => 'required|min:6',
                'role'=> 'required'
            ]);

            $user = User::create([
                'nom' => $request->nom,
                'prenom' => $request->prenom,
                'adresse' => $request->adresse,
                'telephone' => $request->telephone,
                'email' => $request->email,
                'password' => bcrypt($request->password),
                'roles' => $request->role,

            ]);
            $user -> assignRole($request->role);
            $user->createToken('user Password Grand Client')->accessToken;
            return response()->json("token", 200);

        }


        public function getAllUser() {
            $this->middleware(['role:pharmacien']);
            $data = User::where('roles','!=', "pharmacien")->get();
            if(is_null($data)){
                return response()->json(['message'=>'pas encore fait de depense']);
            }
            return response()->json($data, 200);
        }
        public function getUserById($id) {
            $user = User::find($id);
            if(is_null($user)) {
                return response()->json(['message' => 'produit non trouvé'], 404);
            }
            return response()->json($user, 200);
        }

        public function updateUser(Request $request, $id) {

            $user = User::find($id);
            if(is_null($user)) {
                return response()->json(['message' => 'produit n\'existe pas'], 404);
            }
            $user->update($request->all());
            return response($user, 200);
        }

    public function deleteUser($id) {
        $user = User::find($id);
        if(is_null($user)) {
            return response()->json(['message' => 'produit n\'existe pas'], 404);
        }
        $user->delete();
        return response()->json(null, 204);
    }


    //ajout stock
    public function addStock(Request $request){
        $this->validate($request,[
            'qteEntrer' => 'required',
            'date' => 'required',
            'produit' => 'required',
        ]);

        $data = Stock::where('produit',$request->produit)->first();
        if( $data){
            $data->update(['qteEntrer'=> $data->qteEntrer+$request->qteEntrer]);
            $data->update(['date'=>$request->date]);
            return response()->json([$data]);
        }
        else {
            $stock = new Stock();
            $stock->qteEntrer = $request->qteEntrer;
            $stock->date = $request->date;
            $stock->produit = $request->produit;
            $stock->save();
            return response()->json([
                'stock' =>$stock
            ],200);
        }


    }

    public function getAllStock() {
        $data = Stock::all();
        return response()->json($data, 200);
    }
    public function getStockById($id) {
        $user = Stock::find($id);
        if(is_null($user)) {
            return response()->json(['message' => 'produit non trouvé'], 404);
        }
        return response()->json($user, 200);
    }

    public function updateStock(Request $request, $id) {

        $user = Stock::find($id);
        if(is_null($user)) {
            return response()->json(['message' => 'produit n\'existe pas'], 404);
        }
        $user->update($request->all());
        return response($user, 200);
    }

public function deleteStock($id) {
    $user = Stock::find($id);
    if(is_null($user)) {
        return response()->json(['message' => 'produit n\'existe pas'], 404);
    }
    $user->delete();
    return response()->json(null, 204);
}




// ajout de fournisseurs

}
