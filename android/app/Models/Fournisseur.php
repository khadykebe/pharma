<?php

namespace App\Models;


use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Fournisseur extends Model
{
    use HasFactory;

    protected $fillable = [
        'nom',
        'prenom',
        'adresse',
        'telephone',
        'email'
        
    ];


    public function founisseur()
    {
        return $this->hasMany(Commande::class);
    }


    public function founisseurLivrer()
    {
        return $this->hasMany(Livrer::class);
    }
}
