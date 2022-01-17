<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Produit extends Model
{
    use HasFactory;
    protected $fillable = [
        'nom',
        'prixU',
        'prixP',

    ];


    public function famille()
    {
        return $this->belongsTo(FamilleProduit::class);
    }

    public function produitVente()
    {
        return $this->hasMany(Vente::class);

    }

    public function produitCommande()
    {
        return $this->hasMany(Commande::class);
    }


    public function produitLiver()
    {
        return $this->hasMany(Livrer::class);
    }

    public function fournisseurLiver()
    {
        return $this->hasMany(Livrer::class);
    }

    public function commandeLiver()
    {
        return $this->hasMany(Livrer::class);
    }

    public function produitStok()
    {
        return $this->belongsTo(Stock::class);
    }

    public function produitF()
    {
        return $this->hasMany(Facture::class);
    }
}
