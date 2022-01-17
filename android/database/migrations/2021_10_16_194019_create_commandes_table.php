<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateCommandesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('commandes', function (Blueprint $table) {
            $table->id();
            $table->integer('qte')->require();
            $table->date('date')->require();
            $table->bigInteger('produit_id')->unsigned()->nullable();
            $table->bigInteger('fournisseur_id')->unsigned()->nullable();

            $table->foreign('produit_id')->references('id')->on('produits');
            $table->foreign('fournisseur_id')->references('id')->on('fournisseurs');


            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('commandes');
    }
}
