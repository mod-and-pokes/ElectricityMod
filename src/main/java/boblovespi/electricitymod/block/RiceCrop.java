package boblovespi.electricitymod.block;

import boblovespi.electricitymod.initialization.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Willi on 4/11/2017.
 */
public class RiceCrop extends BlockBush implements IGrowable, EMBlock
{
	private static final PropertyInteger Age = PropertyInteger
			.create("age", 0, 7);
	private static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0, 0, 0,
			1, 1 / 16, 1);

	public RiceCrop()
	{
		setDefaultState(
				blockState.getBaseState().withProperty(AgeProperty(), 0));
		setTickRandomly(true);

		setHardness(0);
		setSoundType(SoundType.PLANT);
		disableStats();

		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
	}

	protected int getAge(IBlockState state)
	{
		return state.getValue(AgeProperty());
	}

	public IBlockState WithAge(int age)
	{
		return getDefaultState().withProperty(this.AgeProperty(), age);
	}

	public int MaxAge()
	{
		return 7;
	}

	protected PropertyInteger AgeProperty()
	{
		return Age;
	}

	@Override public boolean canGrow(World world, BlockPos blockPos,
			IBlockState iBlockState, boolean b)
	{
		return !isMaxAge(iBlockState);
	}

	private boolean isMaxAge(IBlockState state)
	{
		if (state.getValue(Age) == MaxAge())
			return true;
		else
			return false;
	}

	@Override public boolean canUseBonemeal(World world, Random random,
			BlockPos blockPos, IBlockState iBlockState)
	{
		return true;
	}

	@Override public void grow(World world, Random random, BlockPos blockPos,
			IBlockState iBlockState)
	{
		world.setBlockState(blockPos,
				WithAge(getAge(iBlockState) + 1 > MaxAge() ?
						MaxAge() :
						getAge(iBlockState) + 1), 2);
	}

	@Override protected boolean canSustainBush(IBlockState state)
	{
		return state.getBlock() == Blocks.FARMLAND;
	}

	@Override protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, Age);
	}

	@Override public List<ItemStack> getDrops(IBlockAccess blockAccess,
			BlockPos pos, IBlockState state, int i)
	{
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

		if (isMaxAge(state))
		{
			drops.add(new ItemStack(ItemInit.riceGrain.toItem(),
					(int) (Math.random() * 3)));
		}
		drops.add(new ItemStack(ItemInit.riceGrain.toItem()));

		return drops;
	}

	@Override public int getMetaFromState(IBlockState state)
	{
		return state.getValue(AgeProperty());
	}

	@Override public IBlockState getStateFromMeta(int meta)
	{
		return WithAge(meta);
	}

	public String UNLOCALIZED_NAME()
	{
		return "rice_crop";
	}

	public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return UNLOCALIZED_NAME();
	}

	@Override public Block toBlock()
	{
		return this;
	}

	@Override public void updateTick(World worldIn, BlockPos pos,
			IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);

		if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
		{
			int i = getAge(state);

			if (i < MaxAge())
			{
				float f = GrowthChance(this, worldIn, pos);

				if (ForgeHooks.onCropsGrowPre(worldIn, pos, state,
						rand.nextInt((int) (25.0F / f) + 1) == 0))
				{
					worldIn.setBlockState(pos, WithAge(i + 1), 2);
					ForgeHooks.onCropsGrowPost(worldIn, pos, state,
							worldIn.getBlockState(pos));
				}
			}
		}
	}

	private float GrowthChance(RiceCrop riceCrop, World w, BlockPos pos)
	{
		float chance = 0;

		BlockPos d = pos.down();
		if (w.getBlockState(d).getBlock()
				.canSustainPlant(w.getBlockState(pos), w, pos, EnumFacing.UP,
						riceCrop))
			chance += 3;
		else
			return 0;

		BlockPos eD = d.east(), wD = d.west(), nD = d.north(), sD = d.south();

		if (w.getBlockState(eD).getBlock() == Blocks.WATER
				|| w.getBlockState(eD).getBlock() == Blocks.FLOWING_WATER)
			chance += 2;
		if (w.getBlockState(wD).getBlock() == Blocks.WATER
				|| w.getBlockState(wD).getBlock() == Blocks.FLOWING_WATER)
			chance += 2;
		if (w.getBlockState(sD).getBlock() == Blocks.WATER
				|| w.getBlockState(sD).getBlock() == Blocks.FLOWING_WATER)
			chance += 2;
		if (w.getBlockState(sD).getBlock() == Blocks.WATER
				|| w.getBlockState(nD).getBlock() == Blocks.FLOWING_WATER)
			chance += 2;

		if (w.getBlockState(d).getBlock().isFertile(w, d))
			chance += 4;

		return chance;
	}
}
