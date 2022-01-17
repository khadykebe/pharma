<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Commande extends Model
{
    use HasFactory;
    protected $fillable = [
        'qte',
        'date',
        
    ];


    public function produit()
    {
        return $this->belongsTo(Produit::class);
    }

    public function fournisseur()
    {
        return $this->hasMany(Fournisseur::class);
    }

    public function Livrer()
    {
        return $this->hasMany(Livrer::class);
    }
    
}
