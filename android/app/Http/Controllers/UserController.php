<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class UserController extends Controller
{
   
    public function login(Request $request)
    {
        $request->validate([
            'email'=>'required|string|email|max:255|exists:users|email',
            'password'=>'required|string|min:6'
        ]);
        $user=User::where('email',$request->email)->first();
        
        if(Hash::check($request->password,$user->password)){
            $token = $user->createToken('user Password Grand Client')->accessToken;
            
            return response()->json([
                'token'=> $token,
                'user' => $user,
                // 'roles' => collect($user->roles)->pluck('name')
            ],200);
        }
        else{
            return response()->json([
                'message'=>'email  ou mot de passe incorrect',
            ],422);
        }

    }
}
