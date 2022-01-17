<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Vente extends Model
{
    use HasFactory;
    protected $fillable = [
        'qte',
        'verse',
        'rendu',
        'produit'
    ];

    public function produit()
    {
        return $this->belongsTo(Produit::class);
    }


    public function vente()
    {
        return $this->hasMany(Facture   ::class);
    }


}
